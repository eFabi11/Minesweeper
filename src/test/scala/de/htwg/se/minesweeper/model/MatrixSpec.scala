package de.htwg.se.minesweeper.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MatrixSpec extends AnyFlatSpec with Matchers {

  "A Matrix" should "initialize with a size and filling value correctly" in {
    val size = 3
    val filling = 0
    val matrix = new Matrix(size, filling)

    matrix.size should be(size)
    for (row <- 0 until size; col <- 0 until size) {
      matrix.cell(row, col) should be(filling)
    }
  }

  it should "return the correct cell value" in {
    val rows = Vector(
      Vector(1, 2, 3),
      Vector(4, 5, 6),
      Vector(7, 8, 9)
    )
    val matrix = Matrix(rows)

    matrix.cell(0, 0) should be(1)
    matrix.cell(1, 1) should be(5)
    matrix.cell(2, 2) should be(9)
  }

  it should "return the correct row" in {
    val rows = Vector(
      Vector(1, 2, 3),
      Vector(4, 5, 6),
      Vector(7, 8, 9)
    )
    val matrix = Matrix(rows)

    matrix.row(0) should be(Vector(1, 2, 3))
    matrix.row(1) should be(Vector(4, 5, 6))
    matrix.row(2) should be(Vector(7, 8, 9))
  }

  it should "fill the matrix with a given value" in {
    val size = 3
    val filling = 0
    val matrix = new Matrix(size, 1)
    val filledMatrix = matrix.fill(filling)

    filledMatrix.size should be(size)
    for (row <- 0 until size; col <- 0 until size) {
      filledMatrix.cell(row, col) should be(filling)
    }
  }

  it should "replace a cell with a new value" in {
    val size = 3
    val matrix = new Matrix(size, 0)
    val updatedMatrix = matrix.replaceCell(1, 1, 5)

    updatedMatrix.cell(1, 1) should be(5)
    updatedMatrix.cell(0, 0) should be(0)
    updatedMatrix.cell(2, 2) should be(0)
  }

  it should "map the matrix to a new matrix with a given function" in {
    val rows = Vector(
      Vector(1, 2, 3),
      Vector(4, 5, 6),
      Vector(7, 8, 9)
    )
    val matrix = Matrix(rows)
    val mappedMatrix = matrix.map(_ * 2)

    mappedMatrix.cell(0, 0) should be(2)
    mappedMatrix.cell(1, 1) should be(10)
    mappedMatrix.cell(2, 2) should be(18)
  }
}
