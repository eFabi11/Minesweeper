package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Move, Symbols, Status, Game}
import de.htwg.se.minesweeper.util.InputSource
import de.htwg.se.minesweeper.difficulty.{EasyDifficulty, MediumDifficulty, HardDifficulty}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._

class TUISpec extends AnyWordSpec with Matchers with MockitoSugar {

  "A TUI" should {
    "select difficulty based on user input" in {
      val controller = mock[Controller]
      val inputSource = mock[InputSource]

      when(inputSource.readLine()).thenReturn("e")
      val tui = new TUI(controller, inputSource)
      tui.selectDifficulty()

      verify(controller).setDifficulty(any[EasyDifficulty])
    }

    "handle invalid difficulty input by setting to Easy" in {
      val controller = mock[Controller]
      val inputSource = mock[InputSource]

      when(inputSource.readLine()).thenReturn("x")
      val tui = new TUI(controller, inputSource)
      tui.selectDifficulty()

      verify(controller).setDifficulty(any[EasyDifficulty])
    }

    "handle valid difficulty input for Medium" in {
      val controller = mock[Controller]
      val inputSource = mock[InputSource]

      when(inputSource.readLine()).thenReturn("m")
      val tui = new TUI(controller, inputSource)
      tui.selectDifficulty()

      verify(controller).setDifficulty(any[MediumDifficulty])
    }

    "handle valid difficulty input for Hard" in {
      val controller = mock[Controller]
      val inputSource = mock[InputSource]

      when(inputSource.readLine()).thenReturn("h")
      val tui = new TUI(controller, inputSource)
      tui.selectDifficulty()

      verify(controller).setDifficulty(any[HardDifficulty])
    }

    "process user moves correctly" in {
      val controller = mock[Controller]
      val inputSource = mock[InputSource]

      val field = new Field(3, Symbols.Covered)
      val game = new Game()
      game.setDifficultyStrategy(new EasyDifficulty)
      game.setDifficulty()

      when(controller.field).thenReturn(field)
      when(controller.game).thenReturn(game)
      when(inputSource.readLine()).thenReturn("o00", "q")

      val tui = new TUI(controller, inputSource)
      
      // Start the game
      tui.run()
      
      // Verify that the correct methods on the controller are called
      verify(controller).uncoverField(0, 0)
    }

    "handle game end conditions" in {
      val controller = mock[Controller]
      val inputSource = mock[InputSource]

      val field = new Field(3, Symbols.Covered)
      val game = new Game()
      game.setDifficultyStrategy(new EasyDifficulty)
      game.setDifficulty()
      game.gameState = Status.Won

      when(controller.field).thenReturn(field)
      when(controller.game).thenReturn(game)
      when(inputSource.readLine()).thenReturn("o00")

      val tui = new TUI(controller, inputSource)

      // Process a move that ends the game
      tui.run()

      // Verify that the game state is checked and the loop exits
      verify(controller).uncoverField(0, 0)
    }
  }
}
