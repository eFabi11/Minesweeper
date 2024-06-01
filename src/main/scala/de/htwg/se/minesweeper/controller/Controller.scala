package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.command.{Command, UncoverCommand, FlagCommand}
import de.htwg.se.minesweeper.model.{Field, Game, Status, Symbols}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy

import scala.collection.mutable.Stack

case class Controller(var field: Field, game: Game) extends Observable {
    var isFirstMove: Boolean = true
    private val undoStack: Stack[Command] = Stack()

    def initializeField(): Unit = {
        field = new Field(game.gridSize, Symbols.Covered)
        notifyObservers
    }

    def firstMove(x: Int, y: Int): Unit = {
        field = game.initializeField(x, y)
        notifyObservers
    }

    def uncoverField(x: Int, y: Int): Unit = {
        executeCommand(new UncoverCommand(this, x, y))
    }

    def flagField(x: Int, y: Int): Unit = {
        executeCommand(new FlagCommand(this, x, y))
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

    private def executeCommand(command: Command): Unit = {
        command.execute()
        undoStack.push(command)
    }

    def undo(): Unit = {
        if (undoStack.nonEmpty) {
            val command = undoStack.pop()
            command.undo()
        } else {
            println("Nothing to undo")
        }
    }

    override def toString: String = field.toString
}
