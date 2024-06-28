package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.field.Field

trait FileIOInterface {
  def load: Field
  def save(field: Field): Unit
}
