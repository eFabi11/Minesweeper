package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer
import scala.io.StdIn.readLine
import de.htwg.se.minesweeper.model.Move
import de.htwg.se.minesweeper.util.InputSource
import de.htwg.se.minesweeper.difficulty.DifficultyLevel



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
        val selectedDifficulty = input match {
            case "E" => DifficultyLevel.Easy
            case "M" => DifficultyLevel.Medium
            case "H" => DifficultyLevel.Hard
            case _ => DifficultyLevel.Easy
        }
        controller.game.setDifficulty(selectedDifficulty)
        // Initialisieren Sie hier das Feld basierend auf der ausgewählten Schwierigkeit,
        
        controller.firstMove(0, 0) // Nutzen Sie Standardkoordinaten oder eine andere Logik
    }


    private def userIn2(input: String): Option[Move] = {
        input match {
            case "q" => None
            case _ =>
                val action = if (input.startsWith("o")) "open" else "flag"
                val xAxis = input.charAt(1).asDigit
                val yAxis = input.charAt(2).asDigit
                Some(Move(action, xAxis, yAxis))
        }
    }

    private def parseInputAndPrintLoop(): Unit = {
        println("Enter your move:")
        val input = inputSource.readLine()
        userIn2(input) match {
            case None => System.exit(0)
            case Some(move) =>
                controller.uncoverField(move.x, move.y)
                controller.game.checkGameState()
                if (controller.game.gameState == Status.Lost || controller.game.gameState == Status.Won) {
                    println("Spiel beendet. Status: " + controller.game.gameState)
                    return
                }
                println(controller.field.toString())
                parseInputAndPrintLoop()
        }
    }
}


enum Status:
    case Playing, Won, Lost

     