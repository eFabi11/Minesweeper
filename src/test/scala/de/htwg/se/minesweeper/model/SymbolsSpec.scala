package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SymbolsSpec extends AnyWordSpec with Matchers {

  "Symbols" should {
    "return the correct symbol for integers" in {
      Symbols.fromInt(0) should be(Symbols.Zero)
      Symbols.fromInt(1) should be(Symbols.One)
      Symbols.fromInt(2) should be(Symbols.Two)
      Symbols.fromInt(3) should be(Symbols.Three)
      Symbols.fromInt(4) should be(Symbols.Four)
      Symbols.fromInt(5) should be(Symbols.Five)
      Symbols.fromInt(6) should be(Symbols.Six)
      Symbols.fromInt(7) should be(Symbols.Seven)
      Symbols.fromInt(8) should be(Symbols.Eight)
    }

    "throw an exception for invalid integers" in {
      an[IllegalArgumentException] should be thrownBy Symbols.fromInt(9)
    }
  }
}
