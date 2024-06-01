package de.htwg.se.minesweeper.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MatrixSpec extends AnyFlatSpec with Matchers {

  "A Matrix" should "be created with a specified dimension and a filling element" in {
    val size = 3
    val filling = 0
    val matrix = Matrix(Vector.tabulate(size, size)((_, _) => filling))
    for {
      row <- 0 until size
      col <- 0 until size
    } yield matrix.cell(row, col) should be(filling)
  }

  it should "be created from a Vector of Vectors" in {
    val rows = Vector(Vector(1, 2, 3), Vector(4, 5, 6), Vector(7, 8, 9))
    val matrix = Matrix(rows)
    for {
      row <- 0 until 3
      col <- 0 until 3
    } yield matrix.cell(row, col) should be(rows(row)(col))
  }

  it should "return the correct cell when accessed" in {
    val rows = Vector(Vector(1, 2, 3), Vector(4, 5, 6), Vector(7, 8, 9))
    val matrix = Matrix(rows)
    matrix.cell(0, 0) should be(1)
    matrix.cell(1, 1) should be(5)
    matrix.cell(2, 2) should be(9)
  }

  it should "allow replacing a cell and return a new Matrix" in {
    val rows = Vector(Vector(1, 2, 3), Vector(4, 5, 6), Vector(7, 8, 9))
    val matrix = Matrix(rows)
    val newMatrix = matrix.replaceCell(1, 1, 42)
    newMatrix.cell(1, 1) should be(42)
    matrix.cell(1, 1) should be(5) // Original matrix should remain unchanged
  }

  it should "return the correct row when accessed" in {
    val rows = Vector(Vector(1, 2, 3), Vector(4, 5, 6), Vector(7, 8, 9))
    val matrix = Matrix(rows)
    matrix.row(0) should be(Vector(1, 2, 3))
    matrix.row(1) should be(Vector(4, 5, 6))
    matrix.row(2) should be(Vector(7, 8, 9))
  }

  it should "fill the matrix with a specified filling element" in {
    val size = 3
    val filling = 42
    val matrix = Matrix(Vector.tabulate(size, size)((_, _) => 0))
    val filledMatrix = matrix.fill(filling)
    for {
      row <- 0 until size
      col <- 0 until size
    } yield filledMatrix.cell(row, col) should be(filling)
  }
}
