package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.difficulty.{DifficultyStrategy, EasyDifficulty}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameSpec extends AnyWordSpec with Matchers {

  "A Game" should {
    "set the difficulty strategy" in {
      val game = Game()
      val strategy = new EasyDifficulty
      game.setDifficultyStrategy(strategy)
      game.gridSize should be(0) // gridSize is initially 0 and should change after setDifficulty
      game.setDifficulty()
      game.gridSize should be(3)
    }

    "initialize the field correctly" in {
      val game = Game()
      val strategy = new EasyDifficulty
      game.setDifficultyStrategy(strategy)
      game.setDifficulty()
      val field = game.initializeField(0, 0)
      field shouldBe a[Field]
    }

    "place bombs correctly" in {
      val game = Game()
      game.setDifficultyStrategy(new EasyDifficulty)
      game.setDifficulty()
      val bombMatrix = game.initializeField(0, 0).bombenMatrix
      (for {
        y <- 0 until bombMatrix.size
        x <- 0 until bombMatrix.size
      } yield bombMatrix.cell(y, x)).count(_ == Symbols.Bomb) should be(1)
    }

    "recognize a bomb" in {
      val game = Game()
      val matrix = new Matrix[Symbols](3, Symbols.Bomb)
      game.isBomb(0, 0, matrix) should be(true)
    }

    "calculate numbers correctly" in {
      val game = Game()
      val bombMatrix = new Matrix[Symbols](3, Symbols.Empty).replaceCell(1, 1, Symbols.Bomb)
      val playerMatrix = new Matrix[Symbols](3, Symbols.Covered)
      val updatedMatrix = game.Num(0, 0, bombMatrix, playerMatrix)
      updatedMatrix.cell(0, 0) should not be Symbols.Covered
    }
  }
}
