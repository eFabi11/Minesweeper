package de.htwg.se.minesweeper.model.Field

import de.htwg.se.minesweeper.model.{Matrix, Symbols, IField, Status}
import de.htwg.se.minesweeper.model.Game.Game

case class Field(matrix: Matrix[Symbols], bomben: Matrix[Symbols]) extends IField {
  override def copy(): IField = new Field(matrix.copy(), bomben.copy())
  
  val size: Int = matrix.size

  def cell(row: Int, col: Int): Symbols = matrix.cell(row, col)
  
  val endl = sys.props("line.separator")
  def bar(cellWidth: Int = 3, cellNum: Int = 3): String = (("+" + "-" * cellWidth) * cellNum) + "+" + endl
  def cells(row: Int = 3, cellWidth: Int = 3): String = matrix.row(row).map(_.toString).map(" " * ((cellWidth-1)/2) + _ + " " *((cellWidth -1)/2)).mkString("|","|","|") + endl
  def mesh(cellWidth: Int = 3): String = (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size))

  def isBomb(x: Int, y: Int): Boolean = {
    val si = bomben.size - 1
    if (inArea(x, y, si)) {
      bomben.cell(y, x) == Symbols.Bomb
    } else false
  }

  def inArea(x: Int, y: Int, side: Int): Boolean = x >= 0 && x <= side && y >= 0 && y <= side

  def open(x: Int, y: Int, spiel: Game): Field = {
    val openedMatrix = openCells(x, y, bomben, matrix)
    if (bomben.cell(y, x) == Symbols.Bomb) {
      spiel.gameState = Status.Lost
    }
    copy(matrix = openedMatrix)
  }

  private def openCells(x: Int, y: Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols] = {
    var tmpMatrix = pMatrix
    val si = bMatrix.size - 1

    if (!inArea(x, y, si) || pMatrix.cell(y, x) != Symbols.Covered) return pMatrix

    var minesFound = 0
    if (isBomb(x + 1, y + 1)) minesFound += 1
    if (isBomb(x, y + 1)) minesFound += 1
    if (isBomb(x - 1, y + 1)) minesFound += 1
    if (isBomb(x + 1, y)) minesFound += 1
    if (isBomb(x - 1, y)) minesFound += 1
    if (isBomb(x + 1, y - 1)) minesFound += 1
    if (isBomb(x, y - 1)) minesFound += 1
    if (isBomb(x - 1, y - 1)) minesFound += 1

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

  def flag(x: Int, y: Int): Field = {
    val newMatrix = matrix.replaceCell(y, x, Symbols.Flag)
    copy(matrix = newMatrix)
  }

  override def copy(matrix: Matrix[Symbols] = this.matrix, bomben: Matrix[Symbols] = this.bomben): Field = {
    Field(matrix.map(identity), bomben.map(identity))
  }

  override def toString: String = mesh()
}
