package de.htwg.se.minesweeper.util

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.Symbols._

class FileIOJSONSpec extends AnyFlatSpec with Matchers {

  "A FileIOJSON" should "save and load a game correctly" in {
    val fileIO = new FileIOJSON
    val field = new Field(new Matrix(3, Covered), new Matrix(3, Empty))

    fileIO.save(field)
    val loadedField = fileIO.load

    loadedField.size should be(field.size)
    loadedField.getCell(0, 0) should be(field.getCell(0, 0))
  }
}
