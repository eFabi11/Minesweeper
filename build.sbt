import org.scoverage.coveralls.GitHubActions

val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "minesweeper",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    //libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
    
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    libraryDependencies += "com.google.inject" % "guice" % "5.0.1",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.14" % Test,
      "org.mockito" % "mockito-core" % "5.3.1" % Test,
      "org.scalatestplus" %% "mockito-4-6" % "3.2.14.0" % Test
    )
  )

import org.scoverage.coveralls.Imports.CoverallsKeys._

coverallsTokenFile := sys.env.get("COVERALLS_REPO_TOKEN")
coverallsService := Some(GitHubActions)
coverageExcludedFiles := "de.htwg.se.minesweeper.aview.TUI.scala; de.htwg.se.minesweeper.aview.MinesweeperGUI.scala"


coverageHighlighting := true
coverageFailOnMinimum := false
coverageMinimumStmtTotal := 0
coverageMinimumBranchTotal := 0
coverageMinimumStmtPerPackage := 0
coverageMinimumBranchPerPackage := 0
coverageMinimumStmtPerFile := 0
coverageMinimumBranchPerFile := 0


