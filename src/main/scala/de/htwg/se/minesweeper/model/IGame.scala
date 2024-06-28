package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.field.Field

trait IGame {
  def gridSize: Int
  def bombCount: Int
  var gameState: Status
  def setDifficultyStrategy(strategy: de.htwg.se.minesweeper.difficulty.DifficultyStrategy): Unit
  def setDifficulty(): Unit
  def initializeField(x: Int, y: Int): Field
  def checkGameState(field: Field): Unit
}
