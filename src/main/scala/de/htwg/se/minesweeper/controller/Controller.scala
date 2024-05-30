package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.{Field, Move, Game, Symbols, Status}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.difficulty.{DifficultyStrategy, DifficultyLevels}

case class Controller(var field: Field, game: Game) extends Observable {

    var isFirstMove: Boolean = true

    def initializeField(): Unit = {
        field = new Field(game.gridSize, Symbols.Covered)
        notifyObservers
    }

    def firstMove(x: Int, y: Int): Unit = {
        field = game.initializeField(x, y)
        notifyObservers
    }

    def uncoverField(x: Int, y: Int): Unit = {
        if (game.gameState == Status.Playing) {
            if (isFirstMove) {
                firstMove(x, y)
                isFirstMove = false
            } else if (field.playerMatrix.cell(y, x) == Symbols.Covered) {
                field = field.open(x, y, game)
                checkGameState()
                notifyObservers
            }
        }
    }

    def flagField(x: Int, y: Int): Unit = {
        if (game.gameState == Status.Playing) {
            field = field.flag(x, y)
            checkGameState()
            notifyObservers
        }
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

    override def toString: String = field.toString
}
