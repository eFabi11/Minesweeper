package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.{Symbols,Matrix}
import de.htwg.se.minesweeper.model.Field.Field
import com.google.inject.Inject
import scala.xml.{Elem, PrettyPrinter}

class FileIOXML @Inject() extends FileIOInterface {

  override def load: Field = {
    new Field(new Matrix(3, Symbols.Covered), new Matrix(3, Symbols.Empty))
  }

  override def save(field: Field): Unit = {
    val pw = new java.io.PrintWriter(new java.io.File("field.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(field))
    pw.write(xml)
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
