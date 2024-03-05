package main.scala.de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  "A minesweeper Field" when {
    "new" should {
      "have a proper bar representation" in {
        val field = new Field(3)
        val endl = sys.props("line.separator")
        field.bar() should be(("+" + "-" * 3) * 3 + "+" + endl)
      }

      "have a proper cells representation" in {
        val field = new Field(3)
        val endl = sys.props("line.separator")
        field.cells() should be(("|" + " " * 3) * 3 + "|" + endl)
      }

      "create a correct mesh for default parameters" in {
        val field = new Field(3)
        val endl = sys.props("line.separator")
        val expectedMesh = {
          val singleBar = ("+" + "-" * 3) * 3 + "+" + endl
          val singleCells = ("|" + " " * 3) * 3 + "|" + endl
          (singleBar + singleCells) * 3 + singleBar
        }
        field.mesh() should be(expectedMesh)
      }

      "create a scalable mesh" in {
        val fieldOne = new Field(1)
        val endl = sys.props("line.separator")
        fieldOne.mesh(1, 1) should be("+-+" + endl + "| |" + endl + "+-+" + endl)
      }

      "display the entire field correctly when printed" in {
        val field = new Field(3)
        val endl = sys.props("line.separator")
        field.toString should be(
          (("+---" * 3) + "+" + endl +
            ("|   " * 3) + "|" + endl) * 3 +
            (("+---" * 3) + "+" + endl))
      }
    }

    "compared to another Field" should {
      "be equal if their dimensions are the same" in {
        val fieldA = new Field(3)
        val fieldB = new Field(3)
        val fieldC = new Field(1)

        fieldA should be(fieldB)
        fieldA should not be (fieldC)
      }
    }
  }
}
