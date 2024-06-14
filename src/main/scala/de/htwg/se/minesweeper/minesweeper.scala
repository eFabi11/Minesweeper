package de.htwg.se.minesweeper

import com.google.inject.Guice
import de.htwg.se.minesweeper.aview.{TUI, MinesweeperGUI}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.StdInInputSource

object Minesweeper {
  def main(args: Array[String]): Unit = {
    // Create the injector
    val injector = Guice.createInjector(new MinesweeperModule)
    
    // Get instances from the injector
    val controller = injector.getInstance(classOf[Controller])
    
    // Initialize TUI and GUI
    val tui = new TUI(controller, StdInInputSource)
    val gui = new MinesweeperGUI(controller)
    
    gui.visible = true // Ensure GUI is visible
    tui.run()
  }
}
