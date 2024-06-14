/*package de.htwg.se.minesweeper.aview

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty._

class MinesweeperGUISpec extends AnyFlatSpec with Matchers with MockitoSugar {

  "A MinesweeperGUI" should "update the view correctly" in {
    val controller = mock[Controller]
    val field = mock[Field]
    val game = mock[Game]

    when(controller.field).thenReturn(field)
    when(controller.game).thenReturn(game)
    when(field.size).thenReturn(3)  // Beispielgröße für die Gitterinitialisierung

    val gui = new MinesweeperGUI(controller)
    gui.update()

    verify(controller, times(1)).field
    verify(controller, times(1)).game
  }

  it should "handle difficulty selection" in {
    val controller = mock[Controller]
    val field = mock[Field]
    val game = mock[Game]

    when(controller.field).thenReturn(field)
    when(controller.game).thenReturn(game)
    when(field.size).thenReturn(3)  // Beispielgröße für die Gitterinitialisierung

    val gui = new MinesweeperGUI(controller)
    gui.difficultyComboBox.selection.item = "Medium"
    gui.updateDifficulty()
    verify(controller, times(1)).setDifficulty(isA(classOf[MediumDifficulty]))
  }

  it should "refresh the grid correctly" in {
    val controller = mock[Controller]
    val field = mock[Field]
    val game = mock[Game]

    when(controller.field).thenReturn(field)
    when(controller.game).thenReturn(game)
    when(field.size).thenReturn(3)  // Beispielgröße für die Gitterinitialisierung

    val gui = new MinesweeperGUI(controller)
    gui.refreshGrid()
    verify(controller, atLeastOnce()).field
  }

  it should "show end game dialog on game over" in {
    val controller = mock[Controller]
    val game = mock[Game]

    when(controller.field).thenReturn(mock[Field])
    when(controller.game).thenReturn(game)
    when(game.gameState).thenReturn(Status.Won)

    val gui = new MinesweeperGUI(controller)
    noException should be thrownBy gui.showEndGameDialog(Status.Won)
  }
}
*/