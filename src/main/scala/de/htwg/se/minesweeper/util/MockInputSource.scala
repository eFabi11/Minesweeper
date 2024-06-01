// src/test/scala/de/htwg/se/minesweeper/util/MockInputSource.scala
package de.htwg.se.minesweeper.util

class MockInputSource(inputs: List[String]) extends InputSource {
  private var inputsIterator = inputs.iterator

  override def readLine(): String = {
    if (inputsIterator.hasNext) {
      inputsIterator.next()
    } else {
      ""
    }
  }
}
