package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
    "new" should {
      "be initialized with a specific size and fillings" in {
        val size = 3
        val emptyField = new Field(size, Symbols.Empty)
        emptyField.size shouldBe size
        emptyField.matrix.cell(0, 0) shouldBe Symbols.Empty
        emptyField.bomben.cell(0, 0) shouldBe Symbols.Empty
      }

      "be initialized with a matrix" in {
        val matrix = new Matrix[Symbols](3, Symbols.Empty)
        val fieldWithMatrix = new Field(matrix)
        fieldWithMatrix.size shouldBe matrix.size
      }
    }

    "using bar and cells" should {
      val field = new Field(3, Symbols.Empty)

      "generate a correct bar representation" in {
        field.bar() should include("+---+")
      }

      "generate correct cells representation" in {
        field.cells(0) should include("|   |")
      }

      "generate a correct mesh representation" in {
        field.mesh() should include("+---+---+---+")
        field.mesh() should include("|   |   |   |")
      }
    }

    "checking for bombs" should {
      "correctly identify a cell with a bomb" in {
        val bombMatrix = new Matrix[Symbols](3, Symbols.Empty).replaceCell(1, 1, Symbols.Bomb)
        val field = new Field(bombMatrix, bombMatrix)
        field.isBomb(1, 1, bombMatrix) shouldBe true
        field.isBomb(0, 0, bombMatrix) shouldBe false
      }
    }

    "opening a cell" should {
      "change the state correctly for a non-bomb cell" in {
        val bombMatrix = new Matrix[Symbols](3, Symbols.Empty).replaceCell(1, 1, Symbols.Bomb)
        val coverMatrix = new Matrix[Symbols](3, Symbols.Covered)
        val game = new Game(Status.Playing)
        val field = new Field(coverMatrix, bombMatrix).open(0, 0, game)

        field.playerMatrix.cell(0, 0) should not be Symbols.Covered
      }
    }
  }
}
