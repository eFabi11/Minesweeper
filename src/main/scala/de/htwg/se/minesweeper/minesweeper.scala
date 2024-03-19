package de.htwg.se.minesweeper

import model.{Field, Matrix, Symbols, Game}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.model.Status
import de.htwg.se.minesweeper.util.StdInInputSource

object Minesweeper {
  def main(args: Array[String]): Unit = {
    var msGame = new Game(Status.Playing)

    val diff = msGame.setDifficulty()
    val side = diff._1
    val bombs = diff._2
    msGame.anzahBomben = bombs
    msGame.side = side

    var coveredField = new Field(side, Symbols.Covered)
    val controller = Controller(coveredField, msGame)
    val inputSource = StdInInputSource
    val tui = new TUI(controller, inputSource)
    tui.run()
  }
}
