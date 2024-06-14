package de.htwg.se.minesweeper.model

import scala.util.Random
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.interfaces.IGame

case class Game() extends IGame {

  var gridSize = 0
  var bombCount = 0
  var gameState = Status.Playing

  private var difficultyStrategy: DifficultyStrategy = _

  def setDifficultyStrategy(strategy: DifficultyStrategy): Unit = {
    this.difficultyStrategy = strategy
  }

  def setDifficulty(): Unit = {
    if (difficultyStrategy != null) {
      difficultyStrategy.setDifficulty(this)
    }
  }

  def initializeField(x: Int, y: Int): Field = {
    val bombenMatrix = setBombs(new Matrix(gridSize, Symbols.Empty), bombCount, x, y)
    val playerMatrix = openCells(x, y, bombenMatrix, new Matrix(gridSize, Symbols.Covered))
    new Field(playerMatrix, bombenMatrix)
  }

  private def setBombs(emptyMatrix: Matrix[Symbols], bombCount: Int, x: Int, y: Int): Matrix[Symbols] = {
    var bombsMatrix = emptyMatrix
    val sizeM = emptyMatrix.size
    var placedBombs: Int = 0
    val random = new Random()

    while (placedBombs < bombCount) {
      val randX = random.nextInt(sizeM)
      val randY = random.nextInt(sizeM)
      if (bombsMatrix.cell(randY, randX) != Symbols.Bomb && !(randX == x && randY == y)) {
        bombsMatrix = bombsMatrix.replaceCell(randY, randX, Symbols.Bomb)
        placedBombs += 1
      }
    }
    bombsMatrix
  }

  def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean = {
    val si = m.size - 1
    if (inArea(x, y, si)) {
      m.cell(y, x) == Symbols.Bomb
    } else false
  }

  def inArea(x: Int, y: Int, side: Int): Boolean = x >= 0 && x <= side && y >= 0 && y <= side

  def openCells(x: Int, y: Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols] = {
    var tmpMatrix = pMatrix
    val si = bMatrix.size - 1

    if (!inArea(x, y, si) || pMatrix.cell(y, x) != Symbols.Covered) return pMatrix

    var minesFound = 0
    if (isBomb(x + 1, y + 1, bMatrix)) minesFound += 1
    if (isBomb(x, y + 1, bMatrix)) minesFound += 1
    if (isBomb(x - 1, y + 1, bMatrix)) minesFound += 1
    if (isBomb(x + 1, y, bMatrix)) minesFound += 1
    if (isBomb(x - 1, y, bMatrix)) minesFound += 1
    if (isBomb(x + 1, y - 1, bMatrix)) minesFound += 1
    if (isBomb(x, y - 1, bMatrix)) minesFound += 1
    if (isBomb(x - 1, y - 1, bMatrix)) minesFound += 1

    if (minesFound == 0) {
      tmpMatrix = tmpMatrix.replaceCell(y, x, Symbols.Empty)
      for (dx <- -1 to 1; dy <- -1 to 1 if !(dx == 0 && dy == 0)) {
        tmpMatrix = openCells(x + dx, y + dy, bMatrix, tmpMatrix)
      }
    } else {
      val symb = Symbols.fromInt(minesFound)
      tmpMatrix = tmpMatrix.replaceCell(y, x, symb)
    }
    tmpMatrix
  }

  def checkGameState(field: Field): Unit = {
    val allBombsFlagged = field.matrix.rows.zip(field.bomben.rows).forall {
      case (playerRow, bombRow) =>
        playerRow.zip(bombRow).forall {
          case (playerCell, bombCell) =>
            (playerCell == Symbols.Flag && bombCell == Symbols.Bomb) || bombCell != Symbols.Bomb
        }
    }

    if (allBombsFlagged) {
      this.gameState = Status.Won
      println("You just won!!!")
    }
  }
}
