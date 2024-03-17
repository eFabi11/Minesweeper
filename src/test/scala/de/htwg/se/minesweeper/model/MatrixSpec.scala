package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec {
  "A Matrix of Symbols" when {
    "empty" should {
      "be created by specifying its size and filling element" in {
        val matrix = new Matrix[Symbols](3, Symbols.Empty)
        matrix.size shouldBe 3
        matrix.cell(0, 0) shouldBe Symbols.Empty
        matrix.cell(1, 1) shouldBe Symbols.Empty
        matrix.cell(2, 2) shouldBe Symbols.Empty
      }

      "be created from a Vector of Vectors" in {
        val vectorOfVectors = Vector(Vector(Symbols.Empty), Vector(Symbols.Bomb), Vector(Symbols.One))
        val matrix = Matrix(vectorOfVectors)
        matrix.size shouldBe 3
        matrix.row(0) shouldBe Vector(Symbols.Empty)
        matrix.row(1) shouldBe Vector(Symbols.Bomb)
        matrix.row(2) shouldBe Vector(Symbols.One)
      }
    }

    "being accessed" should {
      "allow access to its cells" in {
        val matrix = Matrix(Vector.tabulate(3, 3)((row, col) => Symbols.Covered))
        matrix.cell(1, 1) shouldBe Symbols.Covered
      }

      "allow access to its rows" in {
        val matrix = Matrix(Vector.tabulate(3, 3)((row, col) => Symbols.Covered))
        matrix.row(1) shouldBe Vector(Symbols.Covered, Symbols.Covered, Symbols.Covered)
      }
    }

    "being modified" should {
      "be filled entirely with a new element" in {
        val initialMatrix = new Matrix[Int](3, 0)
        val filledMatrix = initialMatrix.fill(1)
        filledMatrix.cell(0, 0) shouldBe 1
        filledMatrix.cell(1, 1) shouldBe 1
        filledMatrix.cell(2, 2) shouldBe 1
      }

      "replace a single cell and return a new Matrix" in {
        val initialMatrix = new Matrix[Int](3, 0)
        val modifiedMatrix = initialMatrix.replaceCell(1, 1, 9)
        initialMatrix.cell(1, 1) shouldBe 0
        modifiedMatrix.cell(1, 1) shouldBe 9
      }
    }
  }
}
