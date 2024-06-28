package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.field.Field
import de.htwg.se.minesweeper.model.game.Game

trait IField {
  def size: Int
  def cell(row: Int, col: Int): Symbols
  def open(x: Int, y: Int, game: Game): Field
  def flag(x: Int, y: Int): Field
  def copy(): Field
}
