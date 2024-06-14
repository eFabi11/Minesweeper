package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.interfaces.{IController, ICommand}
import de.htwg.se.minesweeper.model.{Field, Game, Symbols, Status}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.command.{UncoverCommand, FlagCommand}

class Controller(var field: Field, var game: Game) extends Observable with IController {
  private var undoStack: List[ICommand] = Nil
  var isFirstMove: Boolean = true

  def setFirstMove(value: Boolean): Unit = {
    isFirstMove = value
  }

  def setField(newField: Field): Unit = {
    field = newField
  }

  def initializeField(): Unit = {
    field = new Field(game.gridSize, Symbols.Covered)
    notifyObservers()
  }

  def firstMove(x: Int, y: Int): Unit = {
    field = game.initializeField(x, y)
    notifyObservers()
  }

  def uncoverField(x: Int, y: Int): Unit = {
    val cmd = new UncoverCommand(this, x, y)
    executeCommand(cmd)
  }

  def flagField(x: Int, y: Int): Unit = {
    val cmd = new FlagCommand(this, x, y)
    executeCommand(cmd)
  }

  def setDifficulty(strategy: DifficultyStrategy): Unit = {
    game.setDifficultyStrategy(strategy)
    game.setDifficulty()
    initializeField()
  }

  def checkGameState(): Unit = {
    game.checkGameState(field)
    if (game.gameState == Status.Won || game.gameState == Status.Lost) {
      println("Spiel beendet. Status: " + game.gameState)
    }
  }

  def executeCommand(command: ICommand): Unit = {
    command.execute()
    undoStack = command :: undoStack
  }

  def undo(): Unit = {
    undoStack match {
      case Nil => println("Nothing to undo")
      case head :: tail =>
        head.undo()
        undoStack = tail
    }
  }

  def restart(): Unit = {
    undoStack = Nil
    game.gameState = Status.Playing
    isFirstMove = true
    initializeField()
  }

  override def toString: String = field.toString
}
