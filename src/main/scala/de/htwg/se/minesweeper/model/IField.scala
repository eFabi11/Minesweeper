package de.htwg.se.minesweeper.model

trait IField {
  def size: Int
  def cell(row: Int, col: Int): Symbols
  def open(x: Int, y: Int, game: Game.Game): IField
  def flag(x: Int, y: Int): IField
  def copy(): IField
}
