import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.util.InputSource
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.difficulty.{EasyDifficulty, MediumDifficulty, HardDifficulty}

class TUISpec extends AnyFlatSpec with Matchers with MockitoSugar {

  "A TUI" should "update the view correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    val tui = new TUI(controller, inputSource)

    tui.update
    verify(controller).field
  }

  it should "select difficulty correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(inputSource.readLine()).thenReturn("E")
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    val tui = new TUI(controller, inputSource)

    tui.selectDifficulty()
    verify(controller).setDifficulty(any[EasyDifficulty])
  }

  it should "handle user input and move correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(controller.game).thenReturn(new Game())
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    val tui = new TUI(controller, inputSource)

    tui.parseInputAndPrintLoop(List("o00"))
    verify(controller).uncoverField(0, 0)
  }


  it should "handle flag command correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(controller.game).thenReturn(new Game())
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    val tui = new TUI(controller, inputSource)

    tui.parseInputAndPrintLoop(List("f00"))
    verify(controller).flagField(0, 0)
  }

  it should "handle undo command correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(controller.game).thenReturn(new Game())
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    val tui = new TUI(controller, inputSource)

    tui.parseInputAndPrintLoop(List("u"))
    verify(controller).undo()
  }

  it should "exit on quit command" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(controller.game).thenReturn(new Game())
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    val tui = new TUI(controller, inputSource)

    // Mocking System.exit to avoid actually exiting the test runner
    noException should be thrownBy tui.parseInputAndPrintLoop(List("q"))
  }
}
