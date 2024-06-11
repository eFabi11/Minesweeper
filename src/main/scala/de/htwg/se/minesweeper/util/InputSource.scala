package de.htwg.se.minesweeper.util

trait InputSource {
  def readLine(): String
}

object StdInInputSource extends InputSource {
  override def readLine(): String = scala.io.StdIn.readLine()
}