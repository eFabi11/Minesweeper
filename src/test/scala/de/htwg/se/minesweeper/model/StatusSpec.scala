package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatusSpec extends AnyWordSpec with Matchers {
  "The Status enum" should {
    "have a Playing status" in {
      Status.Playing.toString should be("Playing")
    }
    "have a Won status" in {
      Status.Won.toString should be("Won")
    }
    "have a Lost status" in {
      Status.Lost.toString should be("Lost")
    }
    "allow comparison of different statuses" in {
      Status.Playing should not be Status.Won
    }
  }
}
