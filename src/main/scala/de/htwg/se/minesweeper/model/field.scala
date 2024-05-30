package de.htwg.se.minesweeper.model

case class Field(matrix: Matrix[Symbols], bomben: Matrix[Symbols]) {
    def this(size: Int, filling: Symbols) = this(new Matrix(size, filling), new Matrix(size, Symbols.Empty))
    val size = matrix.size
    def this(matrix: Matrix[Symbols]) = this(matrix, matrix)
    var playerMatrix = matrix
    var bombenMatrix = bomben

    val endl = sys.props("line.separator")
    def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + endl
    def cells(row: Int = 3, cellWidth: Int = 3) = playerMatrix.row(row).map(_.toString).map(" " * ((cellWidth-1)/2) + _ + " " *((cellWidth -1)/2)).mkString("|","|","|") + endl
    def mesh(cellWidth: Int = 3) = (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size))

    def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean = {
        val si = m.size - 1
        if (inArea(x, y, si)) {
            if (m.cell(y, x) == Symbols.Bomb) return true
        }
        false
    }

    def inArea(x: Int, y: Int, side: Int): Boolean = x >= 0 && x <= side && y >= 0 && y <= side

    def Num(x: Int, y: Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols] = {
        var tmpMatrix = pMatrix
        val si = bMatrix.size - 1

        if (!(inArea(x, y, si)) || pMatrix.cell(y, x) != Symbols.Covered) return pMatrix

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
                tmpMatrix = Num(x + dx, y + dy, bMatrix, tmpMatrix)
            }
        } else {
            val symb = Symbols.fromInt(minesFound)
            tmpMatrix = tmpMatrix.replaceCell(y, x, symb)
        }
        tmpMatrix
    }

    def open(x: Int, y: Int, spiel: Game): Field = {
        if (bombenMatrix.cell(y, x) == Symbols.Bomb) {
            spiel.gameState = Status.Lost
        } else {
            playerMatrix = Num(x, y, bombenMatrix, playerMatrix)
        }
        new Field(playerMatrix, bombenMatrix)
    }

    def flag(x: Int, y: Int): Field = {
        playerMatrix = playerMatrix.replaceCell(y, x, Symbols.Flag)
        new Field(playerMatrix, bombenMatrix)
    }

    override def toString: String = mesh()
}
