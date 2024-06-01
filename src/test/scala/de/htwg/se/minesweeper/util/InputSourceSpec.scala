package de.htwg.se.minesweeper.util

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._

class InputSourceSpec extends AnyFlatSpec with Matchers with MockitoSugar {

  "A MockInputSource" should "return the expected input" in {
    val inputs = List("input1", "input2", "input3")
    val mockInputSource = new MockInputSource(inputs)

    mockInputSource.readLine() should be("input1")
    mockInputSource.readLine() should be("input2")
    mockInputSource.readLine() should be("input3")
  }

  it should "return an empty string if no inputs are set" in {
    val mockInputSource = new MockInputSource(Nil)

    mockInputSource.readLine() should be("")
  }

  "A StdInInputSource" should "read input from standard input" in {
    // Simulieren Sie die Benutzereingabe
    val input = "testInput"
    val stdIn = new java.io.ByteArrayInputStream(input.getBytes)
    Console.withIn(stdIn) {
      StdInInputSource.readLine() should be("testInput")
    }
  }
}
