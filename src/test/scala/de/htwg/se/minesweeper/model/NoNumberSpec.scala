package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class NoNumberSpec extends AnyWordSpec {
  "NoNumber" when {
    "called on a 3x3 Matrix with a bomb in the corner" should {
      "update the adjacent cells correctly and return the correct count of covered cells" in {
        val side = 3
        // Erstellen einer Bombe in der oberen linken Ecke und überall sonst leer
        val bombMatrix = Matrix(Vector.tabulate(side, side){ (row, col) =>
          if (row == 0 && col == 0) Symbols.Bomb else Symbols.Empty
        })
        val playerMatrix = new Matrix(side, Symbols.Covered)
        val initialCoveredCount = 0

        val (updatedCoveredCount, updatedPlayerMatrix) = NoNumber.noNum(1, 1, bombMatrix, playerMatrix, initialCoveredCount)

        updatedCoveredCount should be (9)
        // Überprüfen, dass die Zellen um die Bombe korrekt aktualisiert wurden
        //updatedPlayerMatrix.cell(0, 0) shouldBe Symbols.Covered // Sollte bedeckt bleiben, da es eine Bombe ist
        updatedPlayerMatrix.cell(0, 1) shouldBe Symbols.One // Sollte aktualisiert werden, da es an eine Bombe angrenzt
        updatedPlayerMatrix.cell(1, 0) shouldBe Symbols.One // Sollte aktualisiert werden, da es an eine Bombe angrenzt
        updatedPlayerMatrix.cell(1, 1) shouldBe Symbols.One // Sollte aktualisiert werden, da es an eine Bombe angrenzt
        // Stellen Sie sicher, dass die restlichen Zellen unberührt bleiben
        updatedPlayerMatrix.cell(2, 2) shouldBe Symbols.Covered
        updatedPlayerMatrix.cell(1, 2) shouldBe Symbols.Covered
        updatedPlayerMatrix.cell(2, 1) shouldBe Symbols.Covered
      }
    }
  }
}
