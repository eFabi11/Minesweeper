package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer
import de.htwg.se.minesweeper.model.{Move, Status}
import de.htwg.se.minesweeper.util.InputSource
import de.htwg.se.minesweeper.difficulty.{DifficultyStrategy, EasyDifficulty, MediumDifficulty, HardDifficulty}

import scala.util.{Try, Success, Failure}

class TUI(controller: Controller, inputSource: InputSource) extends Observer {

    override def update: Unit = println(controller.field.toString())

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
            case "E" => new EasyDifficulty
            case "M" => new MediumDifficulty
            case "H" => new HardDifficulty
            case _ => 
                println("Ungültige Eingabe, Standard: Easy")
                new EasyDifficulty
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
        Try(userIn2(input)) match {
            case Success(Some(move)) =>
                move.value match {
                    case "open" => Try(controller.uncoverField(move.x, move.y))
                    case "flag" => Try(controller.flagField(move.x, move.y))
                    case "undo" => Try(controller.undo())
                    case _ => println("Invalid command")
                }
            case Success(None) => System.exit(0)
            case Failure(exception) => println(s"Error: ${exception.getMessage}")
        }
        if (controller.game.gameState == Status.Lost || controller.game.gameState == Status.Won) {
            println("Spiel beendet. Status: " + controller.game.gameState)
            return
        }
        println(controller.field.toString())
        parseInputAndPrintLoop()
    }
}
