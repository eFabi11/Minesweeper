package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer
import de.htwg.se.minesweeper.model.{Move, Symbols, Status}
import de.htwg.se.minesweeper.util.InputSource
import de.htwg.se.minesweeper.difficulty.{DifficultyStrategy, EasyDifficulty, MediumDifficulty, HardDifficulty}

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
                val action = if (input.startsWith("o")) "open" else "flag"
                val xAxis = input.charAt(1).asDigit
                val yAxis = input.charAt(2).asDigit
                Some(Move(action, xAxis, yAxis))
        }
    }

    private def parseInputAndPrintLoop(): Unit = {
        println("Enter your move (format: oXY to open, fXY to flag, q to quit):")
        val input = inputSource.readLine()
        userIn2(input) match {
            case None => System.exit(0)
            case Some(move) =>
                if (move.value == "open") {
                    controller.uncoverField(move.x, move.y)
                } else if (move.value == "flag") {
                    controller.flagField(move.x, move.y)
                }
                if (controller.game.gameState == Status.Lost || controller.game.gameState == Status.Won) {
                    println("Spiel beendet. Status: " + controller.game.gameState)
                    return
                }
                println(controller.field.toString())
                parseInputAndPrintLoop()
        }
    }
}
