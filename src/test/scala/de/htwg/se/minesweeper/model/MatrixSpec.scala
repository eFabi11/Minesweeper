import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.model._

class MatrixSpec extends AnyFlatSpec with Matchers {

  "A Matrix" should "be created with the correct size and filling" in {
    val matrix = new Matrix(3, Symbols.Covered)
    matrix.size should be (3)
    matrix.cell(0, 0) should be (Symbols.Covered)
  }

  it should "replace a cell correctly" in {
    val matrix = new Matrix(3, Symbols.Covered)
    val newMatrix = matrix.replaceCell(1, 1, Symbols.Bomb)
    newMatrix.cell(1, 1) should be (Symbols.Bomb)
  }
}
