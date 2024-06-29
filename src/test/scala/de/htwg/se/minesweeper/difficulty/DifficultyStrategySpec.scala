/* package de.htwg.se.minesweeper.difficulty
import de.htwg.se.minesweeper.model.Game
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DifficultyStrategySpec extends AnyWordSpec with Matchers {

  "An EasyDifficulty strategy" should {
    val game = Game()
    val strategy = new EasyDifficulty
    strategy.setDifficulty(game)

    "set the correct grid size and bomb count" in {
      game.gridSize should be(3)
      game.bombCount should be(1)
    }
  }

  "A MediumDifficulty strategy" should {
    val game = Game()
    val strategy = new MediumDifficulty
    strategy.setDifficulty(game)

    "set the correct grid size and bomb count" in {
      game.gridSize should be(9)
      game.bombCount should be(6)
    }
  }

  "A HardDifficulty strategy" should {
    val game = Game()
    val strategy = new HardDifficulty
    strategy.setDifficulty(game)

    "set the correct grid size and bomb count" in {
      game.gridSize should be(16)
      game.bombCount should be(40)
    }
  }
}
*/