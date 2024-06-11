/*package de.htwg.se.minesweeper.aview

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Symbols, Status}

class MinesweeperGUISpec extends AnyWordSpec with Matchers {

  "A MinesweeperGUI" should {
    val game = new Game()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    val gui = new MinesweeperGUI(controller)

    "show a dialog when the game is won" in {
      game.gameState = Status.Won
      gui.update()
      // Check for dialog display logic
      // This is just a placeholder, as actual dialog checking requires a UI test framework
    }

    "show a dialog when the game is lost" in {
      game.gameState = Status.Lost
      gui.update()
      // Check for dialog display logic
      // This is just a placeholder, as actual dialog checking requires a UI test framework
    }
  }
}*/
