package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatrixTest extends AnyWordSpec with Matchers {

  "A Matrix" should {

    "have a working cell method" in {
      val matrix = Matrix(Vector(
        Vector(1, 2, 3),
        Vector(4, 5, 6),
        Vector(7, 8, 9)
      ))

      matrix.cell(0, 0) should be(1)
      matrix.cell(1, 1) should be(5)
      matrix.cell(2, 2) should be(9)
    }

    "have a working row method" in {
      val matrix = Matrix(Vector(
        Vector(1, 2, 3),
        Vector(4, 5, 6),
        Vector(7, 8, 9)
      ))

      matrix.row(0) should be(Vector(1, 2, 3))
      matrix.row(1) should be(Vector(4, 5, 6))
      matrix.row(2) should be(Vector(7, 8, 9))
    }

    "throw an IndexOutOfBoundsException for invalid indices" in {
      val matrix = Matrix(Vector(
        Vector(1, 2, 3),
        Vector(4, 5, 6),
        Vector(7, 8, 9)
      ))

      an[IndexOutOfBoundsException] should be thrownBy matrix.cell(3, 3)
      an[IndexOutOfBoundsException] should be thrownBy matrix.row(3)
    }
  }
}
