package de.htwg.se.minesweeper.difficulty

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._
import de.htwg.se.minesweeper.model.Game

class DifficultyStrategySpec extends AnyWordSpec with Matchers with MockitoSugar {

  "An EasyDifficulty strategy" should {
    "set the game difficulty to easy" in {
      val game = mock[Game]
      val easyDifficulty = new EasyDifficulty
      easyDifficulty.setDifficulty(game)
      verify(game).gridSize = 3
      verify(game).bombCount = 1
    }
  }

  "A MediumDifficulty strategy" should {
    "set the game difficulty to medium" in {
      val game = mock[Game]
      val mediumDifficulty = new MediumDifficulty
      mediumDifficulty.setDifficulty(game)
      verify(game).gridSize = 9
      verify(game).bombCount = 6
    }
  }

  "A HardDifficulty strategy" should {
    "set the game difficulty to hard" in {
      val game = mock[Game]
      val hardDifficulty = new HardDifficulty
      hardDifficulty.setDifficulty(game)
      verify(game).gridSize = 16
      verify(game).bombCount = 40
    }
  }
}
