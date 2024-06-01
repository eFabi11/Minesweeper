package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty._
import de.htwg.se.minesweeper.util.MockInputSource
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._

class MinesweeperSpec extends AnyFlatSpec with Matchers with MockitoSugar {

  "The Minesweeper main method" should "initialize the game and start the TUI" in {
    val game = new Game()
    val field = new Field(10, Symbols.Covered)
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("E", "q")) // Simuliere Benutzerinput "E" um die Schwierigkeit zu setzen und "q" um das Spiel zu beenden
    val tui = new TUI(controller, inputSource)

    when(controller.game).thenReturn(game)
    when(controller.field).thenReturn(field)

    // Starte die TUI, dies wird die parseInputAndPrintLoop Methode der TUI aufrufen
    tui.run()

    // Verifiziere, dass die notwendigen Methoden des Controllers aufgerufen wurden
    verify(controller).setDifficulty(any[DifficultyStrategy])
    verify(controller).notifyObservers
  }
}
