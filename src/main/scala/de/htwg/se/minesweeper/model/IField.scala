package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.Game.Game
import de.htwg.se.minesweeper.model.Field.field

trait IField {
  def size: Int
  def cell(row: Int, col: Int): Symbols
  def open(x: Int, y: Int, game: Game): Field
  def flag(x: Int, y: Int): Field
  def copy(): Field
}
