package de.htwg.se.minesweeper.aview
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.mockito.Mockito.{mock, when}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.util.InputSource

class TUISpec extends AnyFlatSpec with Matchers {

  "A TUI" should "handle invalid commands" in {
    val controller = mock(classOf[Controller])
    val inputSource = mock(classOf[InputSource])
    val field = mock(classOf[Field])
    when(controller.field).thenReturn(field)
    when(inputSource.readLine()).thenReturn("invalid command", "q")
    val tui = new TUI(controller, inputSource)
    tui.run()
  }

  it should "update observer multiple times" in {
    val controller = mock(classOf[Controller])
    val inputSource = mock(classOf[InputSource])
    val field = mock(classOf[Field])
    when(controller.field).thenReturn(field)
    // Define the sequence of commands to be returned when readLine is called
    when(inputSource.readLine()).thenReturn("command1", "command2", "q")
    val tui = new TUI(controller, inputSource)
    tui.run()
    // Add assertions here to verify that the observer was updated multiple times
  }
}