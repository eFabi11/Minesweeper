package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.{Field, Game, Symbols, Status}
import de.htwg.se.minesweeper.difficulty.EasyDifficulty
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._

class ControllerSpec extends AnyWordSpec with Matchers with MockitoSugar {

  "A Controller" should {
    "initialize the field" in {
      val game = mock[Game]
      val field = new Field(3, Symbols.Covered) // Use real field here to avoid toString() verification issue
      when(game.gridSize).thenReturn(3)
      when(game.bombCount).thenReturn(1)
      when(game.initializeField(any[Int], any[Int])).thenReturn(field)

      val controller = new Controller(field, game)
      controller.initializeField()

      // Verify that the field is initialized with the correct dimensions
      field.size should be(3)
      field.playerMatrix.cell(0, 0) should be(Symbols.Covered)
    }

    "handle the first move and uncover subsequent moves" in {
      val game = mock[Game]
      val field = new Field(3, Symbols.Covered) // Use real field here
      when(game.gridSize).thenReturn(3)
      when(game.bombCount).thenReturn(1)
      when(game.initializeField(any[Int], any[Int])).thenReturn(field)
      when(game.gameState).thenReturn(Status.Playing)

      val controller = new Controller(field, game)
      controller.uncoverField(0, 0)

      // Verify that the first move initializes the field
      verify(game).initializeField(0, 0)
      controller.isFirstMove = false
      controller.uncoverField(0, 1)

      // Verify that the open method is called
      field.playerMatrix.cell(0, 1) should not be Symbols.Covered
    }

    "set the difficulty" in {
      val game = mock[Game]
      val field = new Field(3, Symbols.Covered) // Use real field here
      when(game.gridSize).thenReturn(3)
      when(game.bombCount).thenReturn(1)

      val controller = new Controller(field, game)
      controller.setDifficulty(new EasyDifficulty)

      // Verify that the game difficulty is set
      verify(game).setDifficultyStrategy(any[EasyDifficulty])
      verify(game).setDifficulty()
    }
  }
}
