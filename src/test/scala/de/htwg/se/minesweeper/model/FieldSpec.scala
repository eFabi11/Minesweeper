import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.model._

class FieldSpec extends AnyFlatSpec with Matchers {

  "A Field" should "be properly initialized with a size and filling" in {
    val field = new Field(5, Symbols.Covered)
    field.size should be (5)
    field.playerMatrix.size should be (5)
    field.bombenMatrix.size should be (5)
  }

  it should "handle bomb placement and checks" in {
    val bombMatrix = new Matrix[Symbols](3, Symbols.Empty).replaceCell(1, 1, Symbols.Bomb)
    val field = new Field(bombMatrix)
    field.isBomb(1, 1, bombMatrix) should be (true)
    field.isBomb(0, 0, bombMatrix) should be (false)
  }

  it should "handle opening a cell" in {
    val game = new Game()
    val bombMatrix = new Matrix[Symbols](3, Symbols.Empty).replaceCell(1, 1, Symbols.Bomb)
    val playerMatrix = new Matrix[Symbols](3, Symbols.Covered)
    val field = new Field(playerMatrix, bombMatrix)
    val newField = field.open(0, 0, game)
    newField.playerMatrix.cell(0, 0) should not be Symbols.Covered
  }

  it should "handle flagging a cell" in {
    val playerMatrix = new Matrix[Symbols](3, Symbols.Covered)
    val bombMatrix = new Matrix[Symbols](3, Symbols.Empty)
    val field = new Field(playerMatrix, bombMatrix)
    val newField = field.flag(0, 0)
    newField.playerMatrix.cell(0, 0) should be (Symbols.Flag)
  }
}
