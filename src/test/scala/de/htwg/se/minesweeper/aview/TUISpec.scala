import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.util.StdInInputSource
import de.htwg.se.minesweeper.util.InputSource

class TUISpec extends AnyFlatSpec with Matchers {

  "A TUI" should "update the view correctly" in {
    val game = new Game()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    val tui = new TUI(controller, StdInInputSource)
    tui.update
  }

  it should "select difficulty correctly" in {
    val game = new Game()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    val inputSource = new InputSource {
      override def readLine(): String = "E"
    }
    val tui = new TUI(controller, inputSource)
    tui.selectDifficulty()
    controller.game.gridSize should be (3)
    controller.game.bombCount should be (1)
  }
}
