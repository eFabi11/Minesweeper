import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.Symbols._

class FieldSpec extends AnyFlatSpec with Matchers {

  "A Field" should "be properly initialized with a size and filling" in {
    val field = new Field(3, Covered)
    field.matrix.size should be(3)
    for (x <- 0 until field.matrix.size; y <- 0 until field.matrix.size) {
      field.matrix.cell(x, y) should be(Covered)
    }
  }

  it should "handle bomb placement and checks" in {
    val field = new Field(3, Empty)
    val bombMatrix = new Matrix(3, Empty).replaceCell(1, 1, Bomb)
    val newField = new Field(field.playerMatrix, bombMatrix)
    newField.isBomb(1, 1, bombMatrix) should be(true)
    newField.isBomb(0, 0, bombMatrix) should be(false)
  }

  it should "handle opening a cell" in {
    val field = new Field(3, Covered)
    val game = new Game()
    game.gridSize = 3
    game.bombCount = 1

    // Place a bomb at (1,1)
    val bombMatrix = field.bombenMatrix.replaceCell(1, 1, Bomb)
    val updatedField = new Field(field.playerMatrix, bombMatrix)

    val openedField = updatedField.open(0, 0, game)
    openedField.playerMatrix.cell(0, 0) should not be Covered
    openedField.playerMatrix.cell(1, 1) should be(Covered)
  }

  it should "handle flagging a cell" in {
    val field = new Field(3, Covered)
    val flaggedField = field.flag(0, 0)
    flaggedField.playerMatrix.cell(0, 0) should be(Flag)
  }

  it should "correctly display the field as a string" in {
    val field = new Field(3, Covered)
    field.toString should include ("-")
  }

  it should "handle the Num method correctly when no bombs are nearby" in {
    val field = new Field(3, Covered)
    val emptyMatrix = new Matrix(3, Covered)
    val resultMatrix = field.Num(0, 0, field.bombenMatrix, emptyMatrix)
    resultMatrix.cell(0, 0) should be(Empty)
  }

  it should "handle the Num method correctly when bombs are nearby" in {
    val field = new Field(3, Covered)
    val bombMatrix = new Matrix(3, Empty).replaceCell(1, 1, Bomb)
    val emptyMatrix = new Matrix(3, Covered)
    val resultMatrix = field.Num(0, 0, bombMatrix, emptyMatrix)
    resultMatrix.cell(0, 0) should be(Symbols.One)
  }


  it should "handle open method and set the game status to lost if a bomb is hit" in {
    val field = new Field(3, Covered)
    val game = new Game()
    game.gridSize = 3
    game.bombCount = 1

    // Place a bomb at (1, 1)
    val bombMatrix = field.bombenMatrix.replaceCell(1, 1, Bomb)
    val updatedField = new Field(field.playerMatrix, bombMatrix)

    val openedField = updatedField.open(1, 1, game)
    game.gameState should be(Status.Lost)
  }

  it should "handle open method and reveal the cell if it is not a bomb" in {
    val field = new Field(3, Covered)
    val game = new Game()
    game.gridSize = 3
    game.bombCount = 1

    // Place a bomb at (1, 1)
    val bombMatrix = field.bombenMatrix.replaceCell(1, 1, Bomb)
    val updatedField = new Field(field.playerMatrix, bombMatrix)

    val openedField = updatedField.open(0, 0, game)
    game.gameState should be(Status.Playing)
    openedField.playerMatrix.cell(0, 0) should not be Covered
  }
}
