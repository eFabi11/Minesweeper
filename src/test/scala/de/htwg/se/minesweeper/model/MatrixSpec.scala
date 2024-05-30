package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec with Matchers {
  "A Matrix" when {
    "new" should {
      "be created with a specified dimension and a filling element" in {
        val matrix = new Matrix(3, Symbols.Empty)
        matrix.size should be(3)
        (for {
          y <- 0 until matrix.size
          x <- 0 until matrix.size
        } yield matrix.cell(y, x)).forall(_ == Symbols.Empty) should be(true)
      }

      "be created from a Vector of Vectors" in {
        val vector = Vector(Vector(Symbols.Empty, Symbols.Empty), Vector(Symbols.Empty, Symbols.Empty))
        val matrix = new Matrix(vector)
        matrix.size should be(2)
        (for {
          y <- 0 until matrix.size
          x <- 0 until matrix.size
        } yield matrix.cell(y, x)).forall(_ == Symbols.Empty) should be(true)
      }
    }

    "filled with elements" should {
      "allow access to its cells" in {
        val matrix = new Matrix(3, Symbols.Empty)
        matrix.cell(0, 0) should be(Symbols.Empty)
      }

      "allow replacing a cell and return a new Matrix" in {
        val matrix = new Matrix(3, Symbols.Empty)
        val newMatrix = matrix.replaceCell(0, 0, Symbols.Bomb)
        newMatrix.cell(0, 0) should be(Symbols.Bomb)
      }

      "return the correct row when accessed" in {
        val matrix = new Matrix(3, Symbols.Empty)
        matrix.row(0) should be(Vector(Symbols.Empty, Symbols.Empty, Symbols.Empty))
      }
    }
  }
}
