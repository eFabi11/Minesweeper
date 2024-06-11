package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {

  "A Field" should {
    val field = new Field(3, Symbols.Covered)

    "have the correct size" in {
      field.size should be(3)
    }

    "initialize with covered cells" in {
      field.matrix.rows.flatten.forall(_ == Symbols.Covered) should be(true)
    }

    "open cells correctly" in {
      val game = Game()
      val newField = field.open(1, 1, game)
      newField.cell(1, 1) should not be Symbols.Covered
    }

    "flag cells correctly" in {
      val flaggedField = field.flag(0, 0)
      flaggedField.cell(0, 0) should be(Symbols.Flag)
    }
  }
}
