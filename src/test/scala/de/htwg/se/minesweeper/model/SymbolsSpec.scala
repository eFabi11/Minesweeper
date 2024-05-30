package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class SymbolsSpec extends AnyWordSpec with Matchers {

  "The Symbols enum" should {
    "correctly represent Covered as '-'" in {
      Symbols.Covered.toString should be("-")
    }
    "correctly represent Bomb as '*'" in {
      Symbols.Bomb.toString should be("*")
    }
    "correctly represent Empty as ' '" in {
      Symbols.Empty.toString should be(" ")
    }
    "correctly represent Zero as '0'" in {
      Symbols.Zero.toString should be("0")
    }
    "correctly represent One as '1'" in {
      Symbols.One.toString should be("1")
    }
    "correctly represent Two as '2'" in {
      Symbols.Two.toString should be("2")
    }
    "correctly represent Three as '3'" in {
      Symbols.Three.toString should be("3")
    }
    "correctly represent Four as '4'" in {
      Symbols.Four.toString should be("4")
    }
    "correctly represent Five as '5'" in {
      Symbols.Five.toString should be("5")
    }
    "correctly represent Six as '6'" in {
      Symbols.Six.toString should be("6")
    }
    "correctly represent Seven as '7'" in {
      Symbols.Seven.toString should be("7")
    }
    "correctly represent Eight as '8'" in {
      Symbols.Eight.toString should be("8")
    }
    "correctly represent Flag as 'f'" in {
      Symbols.Flag.toString should be("f")
    }
  }

  "The fromInt method" should {
    "return Zero for 0" in {
      Symbols.fromInt(0) should be(Symbols.Zero)
    }
    "return One for 1" in {
      Symbols.fromInt(1) should be(Symbols.One)
    }
    "return Two for 2" in {
      Symbols.fromInt(2) should be(Symbols.Two)
    }
    "return Three for 3" in {
      Symbols.fromInt(3) should be(Symbols.Three)
    }
    "return Four for 4" in {
      Symbols.fromInt(4) should be(Symbols.Four)
    }
    "return Five for 5" in {
      Symbols.fromInt(5) should be(Symbols.Five)
    }
    "return Six for 6" in {
      Symbols.fromInt(6) should be(Symbols.Six)
    }
    "return Seven for 7" in {
      Symbols.fromInt(7) should be(Symbols.Seven)
    }
    "return Eight for 8" in {
      Symbols.fromInt(8) should be(Symbols.Eight)
    }
    "throw IllegalArgumentException for invalid numbers" in {
      an [IllegalArgumentException] should be thrownBy Symbols.fromInt(-1)
      an [IllegalArgumentException] should be thrownBy Symbols.fromInt(9)
    }
  }
}
