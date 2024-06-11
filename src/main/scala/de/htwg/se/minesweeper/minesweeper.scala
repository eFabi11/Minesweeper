package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Symbols}
import de.htwg.se.minesweeper.util.StdInInputSource
import de.htwg.se.minesweeper.aview.{TUI, MinesweeperGUI}

object Minesweeper {
  def main(args: Array[String]): Unit = {
    val game = new Game()
    val field = new Field(10, Symbols.Covered) // Default field, adjust as necessary
    val controller = new Controller(field, game)
    
    // Initialize TUI and GUI
    val tui = new TUI(controller, StdInInputSource)
    val gui = new MinesweeperGUI(controller)
    
    gui.visible = true // Ensure GUI is visible
    tui.run()
  }
}
