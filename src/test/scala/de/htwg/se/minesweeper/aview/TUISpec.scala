package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Move, Symbols}
import de.htwg.se.minesweeper.util.MockInputSource
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {

  "A TUI" should {
    val game = new Game()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    val inputSource = new MockInputSource(List("e", "o00", "f00", "q"))
    val tui = new TUI(controller, inputSource)

    "parse user input correctly" in {
      val method = tui.getClass.getDeclaredMethod("userIn2", classOf[String])
      method.setAccessible(true)
      val move = method.invoke(tui, "o12").asInstanceOf[Option[Move]]
      move should be(Some(Move("open", 1, 2)))
    }

  }
}
