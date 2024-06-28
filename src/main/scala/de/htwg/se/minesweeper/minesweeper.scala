package de.htwg.se.minesweeper

import com.google.inject.Guice
import de.htwg.se.minesweeper.aview.MinesweeperGUI
import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.util.FileIOInterface

object Minesweeper {
  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new MinesweeperModule)
    val controller = injector.getInstance(classOf[IController])
    val fileIO = injector.getInstance(classOf[FileIOInterface])
    val gui = new MinesweeperGUI(controller, fileIO)
    gui.open() // This method opens the GUI window
  }
}
