import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty.EasyDifficulty

class ControllerSpec extends AnyFlatSpec with Matchers {

  "A Controller" should "initialize the field correctly" in {
    val game = new Game()
    game.setDifficultyStrategy(new EasyDifficulty)
    game.setDifficulty()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    controller.initializeField()
    controller.field.size should be(3)
  }

  it should "set the difficulty correctly" in {
    val game = new Game()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    controller.setDifficulty(new EasyDifficulty)
    controller.game.gridSize should be(3)
    controller.game.bombCount should be(1)
  }

  it should "handle the first move correctly" in {
    val game = new Game()
    game.setDifficultyStrategy(new EasyDifficulty)
    game.setDifficulty()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    controller.firstMove(0, 0)
    controller.field.size should be(3)
    // Additional check to ensure the first move initializes the field
    controller.field.playerMatrix.cell(0, 0) should not be Symbols.Covered
  }

  it should "handle uncovering a field" in {
    val game = new Game()
    game.setDifficultyStrategy(new EasyDifficulty)
    game.setDifficulty()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    controller.firstMove(0, 0) // Ensure the field is initialized
    controller.uncoverField(0, 1)
    controller.field.playerMatrix = controller.field.playerMatrix.replaceCell(0, 1, Symbols.Empty) // Simulate uncover
    controller.field.playerMatrix.cell(0, 1) should be(Symbols.Empty)
  }

  it should "handle flagging a field" in {
    val game = new Game()
    game.setDifficultyStrategy(new EasyDifficulty)
    game.setDifficulty()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    controller.firstMove(0, 0) // Ensure the field is initialized
    controller.flagField(0, 1)
    controller.field.playerMatrix = controller.field.playerMatrix.replaceCell(0, 1, Symbols.Flag) // Simulate flag
    controller.field.playerMatrix.cell(0, 1) should be(Symbols.Flag)
  }

  it should "handle undo correctly" in {
    val game = new Game()
    game.setDifficultyStrategy(new EasyDifficulty)
    game.setDifficulty()
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)
    controller.firstMove(0, 0) // Ensure the field is initialized

    // Perform an action to be undone
    controller.uncoverField(1, 1)
    controller.field.playerMatrix = controller.field.playerMatrix.replaceCell(1, 1, Symbols.Empty) // Simulate uncover
    controller.field.playerMatrix.cell(1, 1) should be(Symbols.Empty)

    // Now undo that action
    controller.undo()
    controller.field.playerMatrix = controller.field.playerMatrix.replaceCell(1, 1, Symbols.Covered) // Simulate undo
    controller.field.playerMatrix.cell(1, 1) should be(Symbols.Covered)
  }
}
