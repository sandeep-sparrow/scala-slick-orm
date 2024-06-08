package com.rockthejvm

import java.time.LocalDate
import java.util.concurrent.atomic.AtomicLong

case class Movie(id: Long, name: String, releaseDate: LocalDate, lengthInMin: Int)

object Movie {
  private val seq = new AtomicLong

  def apply(id: Long, name: String, releaseDate: LocalDate, lengthInMin: Int): Movie = {
    Movie(seq.incrementAndGet(), name, releaseDate, lengthInMin)
  }

  def mapperTo(id: Long, name: String, releaseDate: LocalDate, lengthInMin: Int) =
    apply(id, name, releaseDate, lengthInMin)
}

object SlickTables {
  import slick.jdbc.PostgresProfile.api._

  class MovieTable(tag: Tag) extends Table[Movie](tag, Some("movies")/* <- schema name */, "Movie"){
    def id = column[Long]("movie_id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def releaseDate = column[LocalDate]("release_date")
    def lengthInMin = column[Int]("length_in_min")

    // mapping function to the case class
    override def * = (id, name, releaseDate, lengthInMin) <> ((Movie.mapperTo _).tupled, Movie.unapply)
  }
  // "API Entry Point"
  lazy val movieTable = TableQuery[MovieTable]
}
