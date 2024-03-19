package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.{Matrix, Symbols}

class MatrixSpec extends AnyWordSpec {
<<<<<<< HEAD

  "A Matrix" when {

    "empty" should {
      "be created with a specified dimension and a filling element" in {
        val testMatrix = new Matrix[Symbols](3, Symbols.One)
        testMatrix.size should be(3)
        // Additional checks to ensure all elements are Symbols.One
      }

      "be created from a Vector of Vectors" in {
        val testMatrix2 = Matrix(Vector(Vector(Symbols.One)))
        testMatrix2.size should be(1)
        // Additional checks for the content of testMatrix2
      }
    }

    "filled with elements" should {
      val testMatrix3 = new Matrix[String](3, "F")

      "allow access to its cells" in {
        testMatrix3.cell(0, 0) should be("F")
        // Additional checks for other cells
      }

      "be fillable with a new element" in {
        val resultMatrix = testMatrix3.fill("S")
        resultMatrix.cell(1, 1) should be("S")
        // Check immutability - original matrix should remain unchanged
        testMatrix3.cell(1, 1) should be("F")
      }

      "allow replacing a cell and return a new Matrix" in {
        val resultMatrix2 = testMatrix3.replaceCell(0, 0, "1")
        resultMatrix2.cell(0, 0) should be("1")
        // Check immutability - original matrix should remain unchanged
        testMatrix3.cell(0, 0) should be("F")
      }
    }

    "created from a Vector of Vectors" should {
      val vectorMatrix = Matrix(Vector(Vector(1, 2, 3), Vector(4, 5, 6), Vector(7, 8, 9)))

      "return the correct row when accessed" in {
        vectorMatrix.row(1) should be(Vector(4, 5, 6))
        // Additional checks for other rows
      }
    }

  }
}
=======
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
>>>>>>> 10b1d63ab8e6af42b6b4742fb1d12016ee16971c
