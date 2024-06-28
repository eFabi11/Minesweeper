package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.field.Field
import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.parser._

import java.io._

class FileIOJSON extends FileIOInterface {

  implicit val symbolsEncoder: Encoder[Symbols] = Encoder.encodeString.contramap[Symbols](_.toString)
  implicit val symbolsDecoder: Decoder[Symbols] = Decoder.decodeString.emap {
    case "-" => Right(Symbols.Covered)
    case "*" => Right(Symbols.Bomb)
    case " " => Right(Symbols.Empty)
    case "f" => Right(Symbols.Flag)
    case "0" => Right(Symbols.Zero)
    case "1" => Right(Symbols.One)
    case "2" => Right(Symbols.Two)
    case "3" => Right(Symbols.Three)
    case "4" => Right(Symbols.Four)
    case "5" => Right(Symbols.Five)
    case "6" => Right(Symbols.Six)
    case "7" => Right(Symbols.Seven)
    case "8" => Right(Symbols.Eight)
    case other => Left(s"Unknown symbol: $other")
  }

  override def load: Field = {
    val source = scala.io.Source.fromFile("field.json")
    val jsonString = try source.mkString finally source.close()
    val json = parse(jsonString).getOrElse(Json.Null)
    json.as[Field].getOrElse(new Field(3, Symbols.Covered))
  }

  override def save(field: Field): Unit = {
    val pw = new PrintWriter(new File("field.json"))
    pw.write(field.asJson.noSpaces)
    pw.close()
  }
}