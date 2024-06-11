package de.htwg.se.minesweeper.factory

import de.htwg.se.minesweeper.model.{Field, Symbols}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldFactorySpec extends AnyWordSpec with Matchers {
  "A FieldFactory" should {
    "create a field with the correct size and covered symbols" in {
      val size = 3
      val field = FieldFactory.createField(size)
      field.size should be(size)
      (for {
        x <- 0 until size
        y <- 0 until size
      } yield field.cell(y, x)).forall(_ == Symbols.Covered) should be(true)
    }
  }
}
