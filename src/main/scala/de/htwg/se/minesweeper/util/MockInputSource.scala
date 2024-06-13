package de.htwg.se.minesweeper.util

class MockInputSource(var inputs: List[String]) extends InputSource {
  private var inputsIterator = inputs.iterator

  override def readLine(): String = {
    if (inputsIterator.hasNext) {
      inputsIterator.next()
    } else {
      ""
    }
  }

  def setInputs(newInputs: List[String]): Unit = {
    inputs = newInputs
    inputsIterator = inputs.iterator
  }
}
