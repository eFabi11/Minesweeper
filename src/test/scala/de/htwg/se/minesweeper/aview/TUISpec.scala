package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Symbols, Status, Move}
import de.htwg.se.minesweeper.difficulty.{EasyDifficulty, MediumDifficulty, HardDifficulty}
import de.htwg.se.minesweeper.util.InputSource
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.ArgumentMatchers._

class FakeInputSource(inputs: List[String]) extends InputSource {
  private var currentIndex = 0
  override def readLine(): String = {
    val input = inputs(currentIndex)
    currentIndex += 1
    input
  }
}

class TUISpec extends AnyWordSpec with Matchers with MockitoSugar {

  "The TUI" should {

    "select the difficulty based on user input" in {
      val controller = mock[Controller]
      when(controller.field).thenReturn(new Field(3, Symbols.Covered))

      val easyInput = new FakeInputSource(List("E"))
      val tui = new TUI(controller, easyInput)
      tui.selectDifficulty()
      verify(controller).setDifficulty(any[EasyDifficulty])

      val mediumInput = new FakeInputSource(List("M"))
      val tui2 = new TUI(controller, mediumInput)
      tui2.selectDifficulty()
      verify(controller).setDifficulty(any[MediumDifficulty])

      val hardInput = new FakeInputSource(List("H"))
      val tui3 = new TUI(controller, hardInput)
      tui3.selectDifficulty()
      verify(controller).setDifficulty(any[HardDifficulty])

      val invalidInput = new FakeInputSource(List("X"))
      val tui4 = new TUI(controller, invalidInput)
      tui4.selectDifficulty()
      verify(controller, times(2)).setDifficulty(any[EasyDifficulty])
    }

    "handle user moves correctly" in {
      val controller = mock[Controller]
      when(controller.field).thenReturn(new Field(3, Symbols.Covered))

      val game = mock[Game]
      when(game.gameState).thenReturn(Status.Playing)
      when(controller.game).thenReturn(game)

      val movesInput = new FakeInputSource(List("o00", "f11", "q"))
      val tui = new TUI(controller, movesInput)
      tui.run()
      verify(controller).uncoverField(0, 0)
      verify(controller).flagField(1, 1)
    }
  }
}
