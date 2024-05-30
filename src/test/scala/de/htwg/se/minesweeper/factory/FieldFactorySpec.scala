package de.htwg.se.minesweeper.factory

import de.htwg.se.minesweeper.model.{Field, Symbols}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldFactorySpec extends AnyWordSpec with Matchers {
  "A FieldFactory" should {
    "create a field with the correct size and covered cells" in {
      val field = FieldFactory.createField(3)
      field.size should be(3)
      (for {
        y <- 0 until field.size
        x <- 0 until field.size
      } yield field.playerMatrix.cell(y, x)).forall(_ == Symbols.Covered) should be(true)
    }
  }
}
