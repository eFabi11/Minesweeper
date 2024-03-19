package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Status}
import de.htwg.se.minesweeper.util.InputSource
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito
import org.mockito.Mockito._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar

class TUISpec extends AnyWordSpec with Matchers with MockitoSugar {
  "The TUI" when {
    "a new game is started" should {
      "print the initial field" in {
        // Deine bereits vorhandene Setup-Logik...
      }
    }

    "user enters 'o12'" should {
      "call uncoverField on the controller" in {
        // Deine bereits vorhandene Setup-Logik...
      }
    }

    "user enters 'q'" should {
      "exit the game loop" in {
        // Deine bereits vorhandene Setup-Logik...
      }
    }

    "user inputs an invalid command" should {
    "not crash and prompt again" in {
        val controller = mock[Controller]
        val inputSource = mock[InputSource]
        val game = mock[Game]
        val field = mock[Field]

        when(controller.game).thenReturn(game)
        when(game.state).thenReturn(Status.Playing)
        when(controller.field).thenReturn(field) // Stelle sicher, dass field() einen Wert zurückgibt
        when(field.toString).thenReturn("Initial Field") // Optional: Mock das Verhalten von toString()
        when(inputSource.readLine()).thenReturn("invalid", "q")

        val tui = new TUI(controller, inputSource)
        tui.run()

        verify(inputSource, Mockito.atLeast(2)).readLine()
    }
    }

    "the game state changes to Won or Lost" should {
      "exit the game loop after printing the final field state" in {
         "exit the game loop after printing the final field state" in {
        val controller = mock[Controller]
        val game = mock[Game]
        val inputSource = mock[InputSource]

        when(controller.game).thenReturn(game)
        when(inputSource.readLine()).thenReturn("o12")
        when(game.state).thenReturn(Status.Won) // or Status.Lost for another scenario

        val tui = new TUI(controller, inputSource)
        tui.run()

        // Ensure the final field state is printed
        verify(controller, times(1)).field
        // Ensure the game exits after changing the state
        verify(inputSource, atLeastOnce()).readLine()
      }
    }
}
  }
}
