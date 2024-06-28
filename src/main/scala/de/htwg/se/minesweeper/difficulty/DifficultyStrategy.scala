package de.htwg.se.minesweeper.difficulty

import de.htwg.se.minesweeper.model.game.Game

trait DifficultyStrategy {
  def setDifficulty(game: Game): Unit
}

class EasyDifficulty extends DifficultyStrategy {
  override def setDifficulty(game: Game): Unit = {
    game.gridSize = 3
    game.bombCount = 1
  }
}

class MediumDifficulty extends DifficultyStrategy {
  override def setDifficulty(game: Game): Unit = {
    game.gridSize = 9
    game.bombCount = 6
  }
}

class HardDifficulty extends DifficultyStrategy {
  override def setDifficulty(game: Game): Unit = {
    game.gridSize = 16
    game.bombCount = 40
  }
}
