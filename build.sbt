import org.scoverage.coveralls.GitHubActions

val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "minesweeper",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    //libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
    
   libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "3.2.14",
    "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    "com.google.inject" % "guice" % "5.0.1",
    "org.scalatest" %% "scalatest" % "3.2.14" % Test,
    "org.mockito" % "mockito-core" % "5.3.1" % Test,
    "org.scalatestplus" %% "mockito-4-6" % "3.2.14.0" % Test,
    "org.scala-lang.modules" %% "scala-xml" % "2.0.1", // XML support
    "io.circe" %% "circe-core" % "0.14.1", // Circe core
    "io.circe" %% "circe-generic" % "0.14.1", // Circe generic
    "io.circe" %% "circe-parser" % "0.14.1" // Circe parser
)
  )
import org.scoverage.coveralls.Imports.CoverallsKeys._

coverallsTokenFile := sys.env.get("COVERALLS_REPO_TOKEN")
coverallsService := Some(GitHubActions)

coverageHighlighting := true
coverageFailOnMinimum := false
coverageMinimumStmtTotal := 0
coverageMinimumBranchTotal := 0
coverageMinimumStmtPerPackage := 0
coverageMinimumBranchPerPackage := 0
coverageMinimumStmtPerFile := 0
coverageMinimumBranchPerFile := 0


mainClass in (Compile, run) := Some("de.htwg.se.minesweeper.Minesweeper")