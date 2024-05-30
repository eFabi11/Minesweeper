package de.htwg.se.minesweeper.difficulty

object DifficultyLevels {
  sealed trait Level {
    def gridSize: Int
    def bombCount: Int
  }

  case object Easy extends Level {
    val gridSize = 3
    val bombCount = 1 
  }

  case object Medium extends Level {
    val gridSize = 9
    val bombCount = 6 
  }

  case object Hard extends Level {
    val gridSize = 16
    val bombCount = 40 
  }
}
