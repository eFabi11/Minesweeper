/*package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Symbols}
import de.htwg.se.minesweeper.util.InputSource
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar

class FakeInputSource(inputs: List[String]) extends InputSource {
  private val inputIterator = inputs.iterator
  override def readLine(): String = if (inputIterator.hasNext) inputIterator.next() else ""
}

class MinesweeperSpec extends AnyWordSpec with Matchers with MockitoSugar {

  "The Minesweeper main method" should {

    "initialize the game and run the TUI" in {
      // Fake input sequence
      val inputs = List("E", "q")
      val fakeInputSource = new FakeInputSource(inputs)

      // Mock the necessary components
      val game = mock[Game]
      val field = new Field(10, Symbols.Covered)
      val controller = new Controller(field, game)

      // Run the main method with the fake input source
      Minesweeper.main(Array.empty, fakeInputSource)

      // Verify interactions
      verify(game, atLeastOnce()).gridSize
    }
  }
}
*/