package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MoveSpec extends AnyWordSpec with Matchers {
  "The Move" should {
    "have a String, and x,y position" in {
      val move = Move("open", 1, 2)
      move.value should be("open")
      move.x should be(1)
      move.y should be(2)
    }
  }
}
