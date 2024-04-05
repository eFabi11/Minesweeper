package de.htwg.se.minesweeper.model

import scala.util.Random
import de.htwg.se.minesweeper.difficulty.DifficultyLevel
import de.htwg.se.minesweeper.model.{Field, Matrix, Symbols, Status}

case class Game(var difficulty: DifficultyLevel.Level = DifficultyLevel.Easy) {
    var gameState = Status.Playing

    def setDifficulty(level: DifficultyLevel.Level): Unit = {
        difficulty = level
    }

    // This function is now designed to initialize the game field after the first move.
    // It makes sure the player's first move is on a safe spot by not placing bombs until after this move.
    def initializeField(x: Int, y: Int): Field = {
        val emptyMatrix = new Matrix(difficulty.gridSize, Symbols.Covered)
        val bombMatrix = setBombs(emptyMatrix, difficulty.bombCount, x, y)
        val playerMatrix = reveal(x, y, bombMatrix, new Matrix(difficulty.gridSize, Symbols.Covered))
        new Field(playerMatrix, bombMatrix)
    }

    def inArea(x: Int, y: Int, size: Int): Boolean = x >= 0 && x < size && y >= 0 && y < size

    // Recursively reveal cells starting from (x, y)
    def reveal(x: Int, y: Int, bombMatrix: Matrix[Symbols], playerMatrix: Matrix[Symbols]): Matrix[Symbols] = {
        if (!inArea(x, y, bombMatrix.size) || playerMatrix.cell(y, x) != Symbols.Covered) {
            return playerMatrix
        }

        val minesAround = countMinesAround(x, y, bombMatrix)

        if (minesAround == 0) {
            var updatedMatrix = playerMatrix.replaceCell(y, x, Symbols.Empty)
            for (dx <- -1 to 1; dy <- -1 to 1 if dx != 0 || dy != 0) {
                updatedMatrix = reveal(x + dx, y + dy, bombMatrix, updatedMatrix)
            }
            updatedMatrix
        } else {
            playerMatrix.replaceCell(y, x, Symbols.fromInt(minesAround))
        }
    }

    def setBombs(emptyMatrix: Matrix[Symbols], bombCount: Int, x: Int, y: Int): Matrix[Symbols] = {
        val forbidden = (x, y)
        var bombsPlaced = 0
        var bombMatrix = emptyMatrix
        val random = new Random()

        while (bombsPlaced < bombCount) {
            val randX = random.nextInt(emptyMatrix.size)
            val randY = random.nextInt(emptyMatrix.size)

            if (bombMatrix.cell(randY, randX) != Symbols.Bomb && (randX, randY) != forbidden) {
                bombMatrix = bombMatrix.replaceCell(randY, randX, Symbols.Bomb)
                bombsPlaced += 1
            }
        }
        bombMatrix
    }

    def countMinesAround(x: Int, y: Int, matrix: Matrix[Symbols]): Int = {
        (for {
            dx <- -1 to 1
            dy <- -1 to 1
            if inArea(x + dx, y + dy, matrix.size) && matrix.cell(y + dy, x + dx) == Symbols.Bomb
        } yield 1).sum
    }

    def isBomb(x: Int, y: Int, matrix: Matrix[Symbols]): Boolean = {
        inArea(x, y, matrix.size) && matrix.cell(y, x) == Symbols.Bomb
    }

    def checkGameState(): Unit = {
    if(this.gameState == Status.Won) println("you just won!!!")
    else if (this.gameState == Status.Lost) println("you just Lost!!!")
    else print("")
    }
}
