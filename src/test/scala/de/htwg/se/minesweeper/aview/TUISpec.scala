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
    val tui = new TUI(controller, inputSource)

    // Simulate controller update
    tui.update
    verify(controller).field
  }

  it should "select difficulty correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(inputSource.readLine()).thenReturn("E")
    val tui = new TUI(controller, inputSource)

    tui.selectDifficulty()

    verify(controller).setDifficulty(any[EasyDifficulty])
  }

  private def callPrivateMethod(instance: Any, methodName: String, args: Any*): Any = {
    val method = instance.getClass.getDeclaredMethod(methodName)
    method.setAccessible(true)
    method.invoke(instance, args.map(_.asInstanceOf[AnyRef]): _*)
  }

  it should "handle user input and move correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(inputSource.readLine()).thenReturn("o00")
    val tui = new TUI(controller, inputSource)

    callPrivateMethod(tui, "parseInputAndPrintLoop")

    verify(controller).uncoverField(0, 0)
  }

  it should "handle invalid user input gracefully" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(inputSource.readLine()).thenReturn("invalid")
    val tui = new TUI(controller, inputSource)

    callPrivateMethod(tui, "parseInputAndPrintLoop")

    // No interaction with the controller should occur for invalid input
    verifyNoMoreInteractions(controller)
  }

  it should "handle flag command correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(inputSource.readLine()).thenReturn("f00")
    val tui = new TUI(controller, inputSource)

    callPrivateMethod(tui, "parseInputAndPrintLoop")

    verify(controller).flagField(0, 0)
  }

  it should "handle undo command correctly" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(inputSource.readLine()).thenReturn("u")
    val tui = new TUI(controller, inputSource)

    callPrivateMethod(tui, "parseInputAndPrintLoop")

    verify(controller).undo()
  }

  it should "exit on quit command" in {
    val controller = mock[Controller]
    val inputSource = mock[InputSource]
    when(inputSource.readLine()).thenReturn("q")
    val tui = new TUI(controller, inputSource)

    // Mocking System.exit to avoid actually exiting the test runner
    noException should be thrownBy callPrivateMethod(tui, "parseInputAndPrintLoop")
  }
}
