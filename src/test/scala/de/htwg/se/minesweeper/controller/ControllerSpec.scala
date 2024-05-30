package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.util.Observer
import de.htwg.se.minesweeper.difficulty.{EasyDifficulty, DifficultyStrategy}  // Ensure to import the correct package
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar

class ControllerSpec extends AnyWordSpec with Matchers with MockitoSugar {

  "A Controller" should {
    "initialize the field" in {
      val game = mock[Game]
      val field = new Field(10, Symbols.Covered)
      val controller = new Controller(field, game)

      controller.initializeField()
      verify(game).gridSize
    }

    "handle the first move and uncover subsequent moves" in {
      val game = mock[Game]
      val field = new Field(10, Symbols.Covered)
      val controller = new Controller(field, game)

      when(game.gameState).thenReturn(Status.Playing)
      when(game.initializeField(0, 0)).thenReturn(field)
      controller.uncoverField(0, 0)
      verify(game).initializeField(0, 0)
    }

    "set the difficulty" in {
      val game = mock[Game]
      val field = new Field(10, Symbols.Covered)
      val controller = new Controller(field, game)

      val difficulty = new EasyDifficulty()
      controller.setDifficulty(difficulty)
      verify(game).setDifficultyStrategy(difficulty)
      verify(game).setDifficulty()
    }
  }
}
