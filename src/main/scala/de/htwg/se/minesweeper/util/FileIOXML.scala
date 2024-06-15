package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.Field.field
import scala.xml.{Elem, PrettyPrinter}

class FileIOXML extends FileIOInterface {

  override def load: Field = {
    val file = scala.xml.XML.loadFile("field.xml")
    val size = (file \\ "field" \ "@size").text.toInt
    val matrix = (file \\ "cell").map { cellNode =>
      val row = (cellNode \ "@row").text.toInt
      val col = (cellNode \ "@col").text.toInt
      val value = (cellNode \ "@value").text match {
        case "-" => Symbols.Covered
        case "*" => Symbols.Bomb
        case " " => Symbols.Empty
        case "f" => Symbols.Flag
        case "0" => Symbols.Zero
        case "1" => Symbols.One
        case "2" => Symbols.Two
        case "3" => Symbols.Three
        case "4" => Symbols.Four
        case "5" => Symbols.Five
        case "6" => Symbols.Six
        case "7" => Symbols.Seven
        case "8" => Symbols.Eight
      }
      (row, col, value)
    }
    val newField = new Field(size, Symbols.Covered)
    matrix.foldLeft(newField) { case (field, (row, col, value)) =>
      field.matrix.replaceCell(row, col, value)
      field
    }
  }

  override def save(field: Field): Unit = {
    val pw = new java.io.PrintWriter(new java.io.File("field.xml"))
    val pp = new PrettyPrinter(120, 4)
    val xml = fieldToXml(field)
    pw.write(pp.format(xml))
    pw.close()
  }

  def fieldToXml(field: Field): Elem = {
    <field size={field.size.toString}>
      {
      for {
        row <- 0 until field.size
        col <- 0 until field.size
      } yield <cell row={row.toString} col={col.toString} value={field.cell(row, col).toString}/>
      }
    </field>
  }
}
