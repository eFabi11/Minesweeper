import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.difficulty._
import de.htwg.se.minesweeper.model.Game

class DifficultyStrategySpec extends AnyFlatSpec with Matchers {

  "A MediumDifficulty" should "set the game to medium difficulty" in {
    val game = new Game()
    val strategy = new MediumDifficulty
    strategy.setDifficulty(game)
    
    game.gridSize should be(9)
    game.bombCount should be(6)
  }

  "A HardDifficulty" should "set the game to hard difficulty" in {
    val game = new Game()
    val strategy = new HardDifficulty
    strategy.setDifficulty(game)
    
    game.gridSize should be(16)
    game.bombCount should be(40)
  }
}
