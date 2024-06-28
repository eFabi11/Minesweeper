package de.htwg.se.minesweeper.interfaces

import scala.swing.Frame

trait IMinesweeperGUI extends Frame {
  def visible: Boolean
  def visible_=(value: Boolean): Unit
}
