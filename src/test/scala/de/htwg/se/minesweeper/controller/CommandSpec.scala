/*package de.htwg.se.minesweeper.command

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Symbols, Game}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CommandSpec extends AnyWordSpec with Matchers {

  "An UncoverCommand" should {
    val game = new Game()
    game.gridSize = 3
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)

    "execute and uncover the field" in {
      val initialField = controller.field.copy()
      val command = new UncoverCommand(controller, 1, 1)
      command.execute()
      controller.field.cell(1, 1) should not be Symbols.Covered
      command.undo()
      controller.field.cell(1, 1) should be(initialField.cell(1, 1))
    }

    "undo and revert the field to previous state" in {
      val initialField = controller.field.copy()
      val command = new UncoverCommand(controller, 1, 1)
      command.execute()
      command.undo()
      controller.field.cell(1, 1) should be(initialField.cell(1, 1))
    }
  }

  "A FlagCommand" should {
    val game = new Game()
    game.gridSize = 3
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)

    "execute and flag the field" in {
      val initialField = controller.field.copy()
      val command = new FlagCommand(controller, 1, 1)
      command.execute()
      controller.field.cell(1, 1) should be(Symbols.Flag)
      command.undo()
      controller.field.cell(1, 1) should be(initialField.cell(1, 1))
    }

    "undo and revert the field to previous state" in {
      val initialField = controller.field.copy()
      val command = new FlagCommand(controller, 1, 1)
      command.execute()
      command.undo()
      controller.field.cell(1, 1) should be(initialField.cell(1, 1))
    }
  }
}
*/