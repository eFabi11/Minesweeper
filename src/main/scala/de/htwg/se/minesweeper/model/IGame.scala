package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.model.Status
import de.htwg.se.minesweeper.model.Field.field

trait IGame {
  var gridSize: Int
  var bombCount: Int
  var gameState: Status

  def setDifficultyStrategy(strategy: DifficultyStrategy): Unit
  def setDifficulty(): Unit
  def initializeField(x: Int, y: Int): Field
  def checkGameState(field: Field): Unit
}
