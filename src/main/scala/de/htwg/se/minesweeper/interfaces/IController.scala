package de.htwg.se.minesweeper.interfaces

import de.htwg.se.minesweeper.model.{Field, Game}
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy

trait IController {
  def field: Field
  def game: Game
  def isFirstMove: Boolean
  def setFirstMove(value: Boolean): Unit

  def initializeField(): Unit
  def firstMove(x: Int, y: Int): Unit
  def uncoverField(x: Int, y: Int): Unit
  def flagField(x: Int, y: Int): Unit
  def setDifficulty(strategy: DifficultyStrategy): Unit
  def checkGameState(): Unit
  def executeCommand(command: ICommand): Unit
  def undo(): Unit
  def restart(): Unit
  def notifyObservers(): Unit
  
  // Add the setField method
  def setField(newField: Field): Unit
}
