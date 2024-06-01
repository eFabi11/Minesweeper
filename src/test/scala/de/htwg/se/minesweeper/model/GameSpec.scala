import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty.{EasyDifficulty, MediumDifficulty, HardDifficulty}

class GameSpec extends AnyFlatSpec with Matchers {

  "A Game" should "set difficulty correctly" in {
    val game = new Game()
    game.setDifficultyStrategy(new EasyDifficulty)
    game.setDifficulty()
    game.gridSize should be (3)
    game.bombCount should be (1)
  }

  it should "initialize the field correctly" in {
    val game = new Game()
    game.setDifficultyStrategy(new EasyDifficulty)
    game.setDifficulty()
    val field = game.initializeField(0, 0)
    field.size should be (3)
  }

  it should "check if a cell is a bomb" in {
    val game = new Game()
    val bombMatrix = new Matrix[Symbols](3, Symbols.Empty).replaceCell(1, 1, Symbols.Bomb)
    game.isBomb(1, 1, bombMatrix) should be (true)
    game.isBomb(0, 0, bombMatrix) should be (false)
  }

  it should "check game state correctly" in {
    val game = new Game()
    val bombMatrix = new Matrix[Symbols](3, Symbols.Empty).replaceCell(1, 1, Symbols.Bomb)
    val playerMatrix = new Matrix[Symbols](3, Symbols.Covered).replaceCell(1, 1, Symbols.Flag)
    val field = new Field(playerMatrix, bombMatrix)
    game.checkGameState(field)
    game.gameState should be (Status.Won)
  }
}
