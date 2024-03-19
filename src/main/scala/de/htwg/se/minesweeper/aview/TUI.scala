package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer



import scala.io.StdIn.readLine
import de.htwg.se.minesweeper.model.Move
import de.htwg.se.minesweeper.util.InputSource


class TUI(controller: Controller, inputSource: InputSource) extends Observer:
  controller.add(this)

  override def update: Unit = println(controller.field.toString())

  def run(): Unit =
    println(controller.field.toString())
    firstMoveInputParser()
    parseInputAndPrintLoop()

  private def userIn2(input: String): Option[Move] =
    input match
      case "q" => None
      case _ =>
        val action = if (input.startsWith("o")) "open" else "open" // Einfacher Ansatz; Anpassen nach Bedarf
        val xAxis = input.charAt(1).asDigit
        val yAxis = input.charAt(2).asDigit
        Some(Move(action, xAxis, yAxis))

  private def parseInputAndPrintLoop(): Unit =
    println("Enter your move:")
    val input = inputSource.readLine()
    userIn2(input) match
      case None => System.exit(0)
      case Some(move) =>
        controller.uncoverField(move.x, move.y, controller.game)
        controller.game.checkGameState(controller.game)
        if (controller.game.gameState == Status.Lost || controller.game.gameState == Status.Won) System.exit(0)
        parseInputAndPrintLoop()

  private def firstMoveInputParser(): Unit =
    println("Enter your move:")
    val input = inputSource.readLine()
    userIn2(input) match
      case None => System.exit(0)
      case Some(move) =>
        controller.firstMove(move.x, move.y, controller.game)
        controller.game.checkGameState(controller.game)
        if (controller.game.gameState == Status.Lost || controller.game.gameState == Status.Won) System.exit(0)


enum Status:
    case Playing, Won, Lost

     