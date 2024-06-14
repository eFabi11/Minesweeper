package de.htwg.se.minesweeper.interfaces

import de.htwg.se.minesweeper.model.{Field, Symbols, Game}

trait IField {
  def size: Int
  def cell(row: Int, col: Int): Symbols
  def open(x: Int, y: Int, game: Game): Field
  def flag(x: Int, y: Int): Field
  def copy(): Field
}
