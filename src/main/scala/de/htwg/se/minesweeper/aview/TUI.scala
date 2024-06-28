package de.htwg.se.minesweeper.aview

import com.google.inject.Inject
import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.util.{InputSource, Observer}
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.model.{Move, Status}
import de.htwg.se.minesweeper.interfaces.ITUI

class TUI @Inject()(controller: IController, inputSource: InputSource) extends Observer with ITUI {

  override def update(): Unit = println(controller.field.toString())

  def run(): Unit = {
    selectDifficulty()
    println(controller.field.toString())
    parseInputAndPrintLoop()
  }

  def selectDifficulty(): Unit = {
    println("Waehle den Schwierigkeitsgrad: [E]asy, [M]edium, [H]ard")
    val input = inputSource.readLine().toUpperCase
    println(s"Input received: $input")
    val selectedStrategy: DifficultyStrategy = input match {
      case "E" => new de.htwg.se.minesweeper.difficulty.EasyDifficulty
      case "M" => new de.htwg.se.minesweeper.difficulty.MediumDifficulty
      case "H" => new de.htwg.se.minesweeper.difficulty.HardDifficulty
      case _ => 
        println("Ungültige Eingabe, Standard: Easy")
        new de.htwg.se.minesweeper.difficulty.EasyDifficulty
    }
    println(s"Difficulty strategy selected: $selectedStrategy")
    controller.setDifficulty(selectedStrategy)
    println("Schwierigkeitsgrad ausgewählt. Hier ist das Spielfeld:")
    println(controller.field.toString())
  }

  private def userIn2(input: String): Option[Move] = {
    input match {
      case "q" => None
      case _ =>
        val action = if (input.startsWith("o")) "open" else if (input.startsWith("u")) "undo" else "flag"
        val xAxis = if (action == "undo") 0 else input.charAt(1).asDigit
        val yAxis = if (action == "undo") 0 else input.charAt(2).asDigit
        Some(Move(action, xAxis, yAxis))
    }
  }

  private def parseInputAndPrintLoop(): Unit = {
    println("Enter your move (format: oXY to open, fXY to flag, u to undo, q to quit):")
    val input = inputSource.readLine()
    scala.util.Try(userIn2(input)) match {
      case scala.util.Success(Some(move)) =>
        move.value match {
          case "open" => scala.util.Try(controller.uncoverField(move.x, move.y))
          case "flag" => scala.util.Try(controller.flagField(move.x, move.y))
          case "undo" => scala.util.Try(controller.undo())
          case _ => println("Invalid command")
        }
      case scala.util.Success(None) => System.exit(0)
      case scala.util.Failure(exception) => println(s"Error: ${exception.getMessage}")
    }
    if (controller.game.gameState == Status.Lost || controller.game.gameState == Status.Won) {
      println("Spiel beendet. Status: " + controller.game.gameState)
      return
    }
    println(controller.field.toString())
    parseInputAndPrintLoop()
  }

  // New method for testing purposes
  def parseInputAndPrintLoop(inputs: List[String]): Unit = {
    inputs.foreach { input =>
      scala.util.Try(userIn2(input)) match {
        case scala.util.Success(Some(move)) =>
          move.value match {
            case "open" => scala.util.Try(controller.uncoverField(move.x, move.y))
            case "flag" => scala.util.Try(controller.flagField(move.x, move.y))
            case "undo" => scala.util.Try(controller.undo())
            case _ => println("Invalid command")
          }
        case scala.util.Success(None) => return
        case scala.util.Failure(exception) => println(s"Error: ${exception.getMessage}")
      }
      if (controller.game.gameState == Status.Lost || controller.game.gameState == Status.Won) {
        println("Spiel beendet. Status: " + controller.game.gameState)
        return
      }
      println(controller.field.toString())
    }
  }
}