package com.rockthejvm

import slick.jdbc.PostgresProfile.api._

object Connection {
  val db = Database.forConfig("postgres") // auto lookup to /resources/application.config file path
}
