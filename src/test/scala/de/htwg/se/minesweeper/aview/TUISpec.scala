import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Status}
import de.htwg.se.minesweeper.util.InputSource
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar

class TUISpec extends AnyWordSpec with Matchers with MockitoSugar {
  "The TUI" when {
    "a new game is started" should {
      "print the initial field" in {
        val controller = mock[Controller]
        val game = mock[Game]
        val field = mock[Field]
        val inputSource = mock[InputSource]
        when(controller.game).thenReturn(game)
        when(game.state).thenReturn(Status.Playing)
        when(controller.field).thenReturn(field)
        when(field.toString).thenReturn("Initial Field")
        when(inputSource.readLine()).thenReturn("q")

        val tui = new TUI(controller, inputSource)
        tui.run()

        verify(field).toString
      }
    }

    "user enters 'o12'" should {
      "call uncoverField on the controller" in {
        val controller = mock[Controller]
        val game = mock[Game]
        val inputSource = mock[InputSource]
        when(controller.game).thenReturn(game)
        when(game.state).thenReturn(Status.Playing)
        when(inputSource.readLine()).thenReturn("o12", "q")

        val tui = new TUI(controller, inputSource)
        tui.run()

        verify(controller).uncoverField(1, 2, game)
      }
    }

    "user enters 'q'" should {
      "exit the game loop" in {
        val controller = mock[Controller]
        val inputSource = mock[InputSource]
        when(inputSource.readLine()).thenReturn("q")

        val tui = new TUI(controller, inputSource)
        tui.run()

        verify(inputSource, atLeastOnce()).readLine()
      }
    }
  }
}
