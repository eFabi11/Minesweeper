package de.htwg.se.minesweeper.model

enum Symbols(representedAsString: String):
  override def toString: String = representedAsString
  case Covered extends Symbols("-")
  case Bomb extends Symbols("*")
  case Empty extends Symbols(" ")
  case Zero extends Symbols("0")
  case One extends Symbols("1")
  case Two extends Symbols("2")
  case Three extends Symbols("3")
  case Four extends Symbols("4")
  case Five extends Symbols("5")
  case Six extends Symbols("6")
  case Seven extends Symbols("7")
  case Eight extends Symbols("8")
  case Flag extends Symbols("F")

object Symbols {
  def fromInt(minesAround: Int): Symbols = minesAround match {
    case 0 => Symbols.Zero
    case 1 => Symbols.One
    case 2 => Symbols.Two
    case 3 => Symbols.Three
    case 4 => Symbols.Four
    case 5 => Symbols.Five
    case 6 => Symbols.Six
    case 7 => Symbols.Seven
    case 8 => Symbols.Eight
    case _ => throw new IllegalArgumentException("Invalid number of mines around")
  }
}
