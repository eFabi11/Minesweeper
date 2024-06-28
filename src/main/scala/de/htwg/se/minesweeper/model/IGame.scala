package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Game.Game
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.model.Status

trait IGame {
  var gridSize: Int
  var bombCount: Int
  var gameState: Status
  def setDifficultyStrategy(strategy: DifficultyStrategy): Unit
  def setDifficulty(): Unit
  def initializeField(x: Int, y: Int): IField
  def checkGameState(field: IField): Unit
}
