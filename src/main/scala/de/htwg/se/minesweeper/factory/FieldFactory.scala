package de.htwg.se.minesweeper.factory

import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.field.Field

object FieldFactory {
  def createField(gridSize: Int): Field = {
    new Field(gridSize, Symbols.Covered)
  }
}
