package de.htwg.se.minesweeper

import com.google.inject.Guice
import de.htwg.se.minesweeper.aview.{TUI, MinesweeperGUI}
import de.htwg.se.minesweeper.controller.Controller.Controller
import de.htwg.se.minesweeper.util.{FileIOInterface, StdInInputSource}

object Minesweeper {
  def main(args: Array[String]): Unit = {
    // Create the injector
    val injector = Guice.createInjector(new MinesweeperModule)
    
    // Get instances from the injector
    val controller = injector.getInstance(classOf[Controller])
    val fileIO = injector.getInstance(classOf[FileIOInterface])
    
    // Initialize TUI and GUI
    val tui = new TUI(controller, StdInInputSource)
    val gui = new MinesweeperGUI(controller, fileIO)
    
    gui.visible = true // Ensure GUI is visible
    tui.run()
  }
}
