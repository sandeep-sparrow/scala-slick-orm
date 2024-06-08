ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "slick-demo-live"
  )

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.5.0",
  "org.postgresql" % "postgresql" % "42.7.3", // postgresql db - is the easiest one
  "com.typesafe.slick" %% "slick-hikaricp" % "3.5.1",
  "com.github.tminglei" %% "slick-pg" % "0.22.2",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.22.2"
)