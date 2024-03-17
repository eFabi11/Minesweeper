package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Number.Num
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class NumberSpec extends AnyWordSpec {
  "The Num function" should {
  "correctly update the player matrix based on nearby bombs" in {
    val side = 3
    val testMatrix = new Matrix[Symbols](side, Symbols.Empty)
    val bombPositions = Seq((1, 1)) // Define bomb positions
    val testBombMatrix = bombPositions.foldLeft(testMatrix) { (matrix, pos) =>
      matrix.replaceCell(pos._1, pos._2, Symbols.Bomb)
    }
    var testPlayerMatrix = new Matrix(side, Symbols.Covered)
    var coveredCount = 0

    // Test updating a cell with one bomb nearby
    val (updatedCoveredCount1, updatedPlayerMatrix1) = Num(0, 0, testBombMatrix, testPlayerMatrix, coveredCount)
    withClue(s"Expected Symbols.One, but found ${updatedPlayerMatrix1.cell(0, 0)}: ") {
      updatedPlayerMatrix1.cell(0, 0) shouldBe Symbols.One
    }

    // Suppose here you intended to test another cell, e.g., (2, 1), for the next operation
    val (updatedCoveredCount2, updatedPlayerMatrix2) = Num(2, 1, testBombMatrix, updatedPlayerMatrix1, updatedCoveredCount1)
    // You can add clues or assertions here as needed for this second operation

    // Continuing to test with the previously updated matrix and covered count
    val (updatedCoveredCount3, updatedPlayerMatrix3) = Num(0, 0, testBombMatrix, updatedPlayerMatrix2, updatedCoveredCount2)
    // Add assertions for the third operation

      // Testing a cell outside the valid area
      val (updatedCoveredCount4, updatedPlayerMatrix4) = Num(-1, -1, testBombMatrix, updatedPlayerMatrix3, updatedCoveredCount3)
      updatedCoveredCount4 shouldBe 2 // Covered count remains unchanged
      updatedPlayerMatrix4 shouldBe updatedPlayerMatrix3 // Matrix remains unchanged

      // Ensure bomb cell remains covered
      updatedPlayerMatrix4.cell(1, 1) shouldBe Symbols.Covered
      testBombMatrix.cell(1, 1) shouldBe Symbols.Bomb
    }
  }
}
