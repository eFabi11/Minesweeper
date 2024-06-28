package de.htwg.se.minesweeper.controller.controller

import com.google.inject.Inject
import de.htwg.se.minesweeper.controller.{ICommand, IController}
import de.htwg.se.minesweeper.model.{Symbols, Status, IGame}
import de.htwg.se.minesweeper.model.field.Field
import de.htwg.se.minesweeper.util.{Observable, Observer}
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.command.{UncoverCommand, FlagCommand, Command}
import de.htwg.se.minesweeper.model.game.Game

class Controller @Inject() (var field: Field, var game: IGame) extends Observable with IController {
  private var undoStack: List[Command] = Nil
  var isFirstMove: Boolean = true

  def setFirstMove(value: Boolean): Unit = {
    isFirstMove = value
  }

  def setField(newField: Field): Unit = {
    field = newField
    notifyObservers()
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
    if (isFirstMove) {
      firstMove(x, y)
      setFirstMove(false)
    } else {
      val cmd = new UncoverCommand(this, x, y)
      executeCommand(cmd)
    }
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

  def executeCommand(command: Command): Unit = {
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

  def addObserver(observer: Observer): Unit = {
    add(observer)
  }

  override def toString: String = field.toString
}
