package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty._
import de.htwg.se.minesweeper.command._
import de.htwg.se.minesweeper.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Controller" should {
    val game = Game()
    val field = new Field(3, Symbols.Covered)
    val controller = Controller(field, game)

    "initialize the field correctly" in {
      controller.setDifficulty(new EasyDifficulty)
      controller.field.size should be(3)
      controller.field.matrix.rows.flatten.count(_ == Symbols.Covered) should be(9)
    }

    "execute uncover command correctly" in {
      controller.uncoverField(1, 1)
      controller.field.cell(1, 1) should not be Symbols.Covered
    }

    "execute flag command correctly" in {
      controller.flagField(0, 0)
      controller.field.cell(0, 0) should be(Symbols.Flag)
    }

    "undo commands correctly" in {
      controller.undo()
      controller.field.cell(0, 0) should be(Symbols.Covered)
    }

    "restart the game correctly" in {
      controller.restart()
      controller.field.size should be(3)
      controller.field.matrix.rows.flatten.count(_ == Symbols.Covered) should be(9)
      controller.isFirstMove should be(true)
    }
  }
}
