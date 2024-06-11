package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec with Matchers {

  "A Matrix" should {
    val matrix = new Matrix(3, Symbols.Covered)

    "have the correct size" in {
      matrix.size should be(3)
    }

    "initialize with the correct filling" in {
      matrix.rows.flatten.forall(_ == Symbols.Covered) should be(true)
    }

    "replace cells correctly" in {
      val newMatrix = matrix.replaceCell(1, 1, Symbols.Flag)
      newMatrix.cell(1, 1) should be(Symbols.Flag)
    }
  }
}
