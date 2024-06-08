package com.rockthejvm

import java.time.LocalDate
import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object PrivateExecutionContext {
  private val executor = Executors.newFixedThreadPool(4)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(executor)
}
object Main {
  import slick.jdbc.PostgresProfile.api._
  import PrivateExecutionContext._

  private val shawShankRedemption = Movie(1, "The ShawShank Redemption", LocalDate.of(1994, 9, 23), 162)

  private def demoInsertMovie()(implicit ec: ExecutionContext): Unit = {
    val queryDescription = SlickTables.movieTable += shawShankRedemption
    val futureId: Future[Int] = Connection.db.run(queryDescription)
    futureId.onComplete{
      case Success(newMovieId) => println(s"Query was successful, new id is $newMovieId")
      case Failure(ex) => println(s"Query failed, reason: $ex")
    }
    Thread.sleep(1000)
  }


  def main(args: Array[String]): Unit = {
    demoInsertMovie()
  }
}
