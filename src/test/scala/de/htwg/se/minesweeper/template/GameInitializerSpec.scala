/*import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.template._

class GameInitializerSpec extends AnyFlatSpec with Matchers {

  "An EasyGameInitializer" should "set the game to easy difficulty and initialize the field" in {
    val game = new Game()
    val initializer = new EasyGameInitializer
    val field = initializer.initializeGame(game, 0, 0)

    game.gridSize should be(3)
    game.bombCount should be(1)
    field.size should be(3)
  }

  "A MediumGameInitializer" should "set the game to medium difficulty and initialize the field" in {
    val game = new Game()
    val initializer = new MediumGameInitializer
    val field = initializer.initializeGame(game, 0, 0)

    game.gridSize should be(9)
    game.bombCount should be(6)
    field.size should be(9)
  }

  "A HardGameInitializer" should "set the game to hard difficulty and initialize the field" in {
    val game = new Game()
    val initializer = new HardGameInitializer
    val field = initializer.initializeGame(game, 0, 0)

    game.gridSize should be(16)
    game.bombCount should be(40)
    field.size should be(16)
  }
}
*/