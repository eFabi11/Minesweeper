package de.htwg.se.minesweeper.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FieldSpec extends AnyFlatSpec with Matchers {

  "A Field" should "initialize correctly" in {
    val field = new Field(3, Symbols.Covered)
    field.size should be (3)
    field.cell(0, 0) should be (Symbols.Covered)
  }

  it should "open cells correctly when no bombs are nearby" in {
    val game = new Game()
    game.gridSize = 3

    // Create a field with no bombs
    val bombMatrix = new Matrix(3, Symbols.Empty)
    val playerMatrix = new Matrix(3, Symbols.Covered)
    val field = new Field(playerMatrix, bombMatrix)

    // Open a cell in the field
    val newField = field.open(1, 1, game)

    // Check if the opened cell and its neighbors are marked as empty
    newField.cell(1, 1) should be (Symbols.Empty)
    newField.cell(0, 0) should be (Symbols.Empty)
    newField.cell(0, 1) should be (Symbols.Empty)
    newField.cell(0, 2) should be (Symbols.Empty)
    newField.cell(1, 0) should be (Symbols.Empty)
    newField.cell(1, 2) should be (Symbols.Empty)
    newField.cell(2, 0) should be (Symbols.Empty)
    newField.cell(2, 1) should be (Symbols.Empty)
    newField.cell(2, 2) should be (Symbols.Empty)
  }
}