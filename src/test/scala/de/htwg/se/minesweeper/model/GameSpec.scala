/*package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.difficulty.EasyDifficulty
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameSpec extends AnyWordSpec with Matchers {

  "A Game" should {
    val game = Game()
    game.setDifficultyStrategy(new EasyDifficulty)
    game.setDifficulty()

    "initialize the game correctly" in {
      game.gridSize should be(3)
      game.bombCount should be(1)
    }

    "initialize field correctly" in {
      val field = game.initializeField(1, 1)
      field.size should be(3)
      field.matrix.rows.flatten.count(_ == Symbols.Covered) should be < 9
    }

    "check game state correctly" in {
      val field = game.initializeField(1, 1)
      game.checkGameState(field)
      game.gameState should (be(Status.Playing) or be(Status.Won) or be(Status.Lost))
    }
  }
}
*/