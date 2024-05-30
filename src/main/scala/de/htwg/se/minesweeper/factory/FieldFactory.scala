package de.htwg.se.minesweeper.factory

import de.htwg.se.minesweeper.model.{Field, Symbols}

object FieldFactory {
  def createField(gridSize: Int): Field = {
    new Field(gridSize, Symbols.Covered)
  }
}
