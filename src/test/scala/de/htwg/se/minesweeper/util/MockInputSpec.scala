package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MockInputSourceSpec extends AnyWordSpec with Matchers {

  "A MockInputSource" should {
    "provide inputs correctly" in {
      val inputs = List("input1", "input2")
      val mockInputSource = new MockInputSource(inputs)

      mockInputSource.readLine() should be("input1")
      mockInputSource.readLine() should be("input2")
    }

    "return empty string when inputs are exhausted" in {
      val mockInputSource = new MockInputSource(List())
      mockInputSource.readLine() should be("")
    }
  }
}
