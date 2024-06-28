package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.Field.Field
import de.htwg.se.minesweeper.model.Matrix
import de.htwg.se.minesweeper.model.Symbols
import com.google.inject.Inject
import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.parser._
import io.circe.syntax._
import java.io.{File, PrintWriter}

class FileIOJSON @Inject() extends FileIOInterface {
  implicit val symbolsEncoder: Encoder[Symbols] = (a: Symbols) => Json.fromString(a.toString)
  implicit val symbolsDecoder: Decoder[Symbols] = (c: HCursor) => for {
    str <- c.as[String]
  } yield Symbols.valueOf(str)

  implicit val fieldEncoder: Encoder[Field] = Encoder.forProduct2("matrix", "bomben")(f => (f.matrix, f.bomben))
  implicit val fieldDecoder: Decoder[Field] = Decoder.forProduct2("matrix", "bomben")(Field.apply)

  override def load: Field = {
    val source = scala.io.Source.fromFile("field.json")
    val jsonString = try source.mkString finally source.close()
    decode[Field](jsonString).getOrElse(new Field(new Matrix(3, Symbols.Covered), new Matrix(3, Symbols.Empty)))
  }

  override def save(field: Field): Unit = {
    val pw = new PrintWriter(new File("field.json"))
    try pw.write(field.asJson.noSpaces) finally pw.close()
  }
}
