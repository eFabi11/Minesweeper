package de.htwg.se.minesweeper.util

trait InputSource {
  def readLine(): String
}

class MockInputSource(inputs: Iterator[String]) extends InputSource {
  override def readLine(): String = if (inputs.hasNext) inputs.next() else ""
}

object StdInInputSource extends InputSource {
  override def readLine(): String = scala.io.StdIn.readLine()
}
