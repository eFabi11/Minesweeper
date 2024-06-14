package de.htwg.se.minesweeper.interfaces

trait IMinesweeperGUI {
  def visible: Boolean
  def visible_=(value: Boolean): Unit
}
