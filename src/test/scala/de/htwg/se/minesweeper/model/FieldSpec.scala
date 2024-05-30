package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
    "created" should {
      "initialize with the correct size and filling" in {
        val field = new Field(3, Symbols.Covered)
        field.size should be(3)
        (for {
          y <- 0 until field.size
          x <- 0 until field.size
        } yield field.playerMatrix.cell(y, x)).forall(_ == Symbols.Covered) should be(true)
      }
    }

    "flagging a cell" should {
      "mark the cell with a flag" in {
        val field = new Field(3, Symbols.Covered)
        val updatedField = field.flag(0, 0)
        updatedField.playerMatrix.cell(0, 0) should be(Symbols.Flag)
      }
    }

    "opening a cell" should {
      "uncover the cell and update the field" in {
        val bombMatrix = new Matrix[Symbols](3, Symbols.Empty)
        val playerMatrix = new Matrix[Symbols](3, Symbols.Covered)
        val field = new Field(playerMatrix, bombMatrix)
        val updatedField = field.open(0, 0, Game())
        updatedField.playerMatrix.cell(0, 0) should not be Symbols.Covered
      }
    }
  }
}
