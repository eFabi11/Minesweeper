package de.htwg.se.minesweeper.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FieldSpec extends AnyFlatSpec with Matchers {

  "A Field" should "initialize correctly" in {
    val field = new Field(3, Symbols.Covered)
    field.size should be (3)
    field.cell(0, 0) should be (Symbols.Covered)
  }

  it should "open cells correctly" in {
    val game = new Game()
    val field = new Field(3, Symbols.Covered)
    val newField = field.open(0, 0, game)

    newField.cell(0, 0) should not be (Symbols.Covered)
  }

  it should "flag cells correctly" in {
    val field = new Field(3, Symbols.Covered)
    val newField = field.flag(0, 0)

    newField.cell(0, 0) should be (Symbols.Flag)
  }

  it should "detect bombs correctly" in {
    val field = new Field(3, Symbols.Covered)
    val bombField = field.copy(bomben = field.bomben.replaceCell(0, 0, Symbols.Bomb))

    bombField.isBomb(0, 0) should be (true)
    bombField.isBomb(1, 1) should be (false)
  }
}
