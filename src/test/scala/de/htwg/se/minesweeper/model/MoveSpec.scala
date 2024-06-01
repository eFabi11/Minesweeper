import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.model.Move

class MoveSpec extends AnyFlatSpec with Matchers {

  "A Move" should "be created with the correct values" in {
    val move = Move("open", 1, 2)
    move.value should be ("open")
    move.x should be (1)
    move.y should be (2)
  }
}
