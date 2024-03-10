package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class AreaSpec extends AnyWordSpec with Matchers {

  "An Area" should {
    "verify that a point is within the specified boundaries" in {
      Area.inArea(0, 0, 10) shouldBe true
      Area.inArea(10, 10, 10) shouldBe true
      Area.inArea(5, 5, 10) shouldBe true
      Area.inArea(-1, 5, 10) shouldBe false
      Area.inArea(5, -1, 10) shouldBe false
      Area.inArea(11, 5, 10) shouldBe false
      Area.inArea(5, 11, 10) shouldBe false
    }
  }
}