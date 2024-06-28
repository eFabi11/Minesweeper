package de.htwg.se.minesweeper

import com.google.inject.Guice
import de.htwg.se.minesweeper.interfaces.{IMinesweeperGUI, ITUI}

object Minesweeper {
  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new MinesweeperModule())

    val tui = injector.getInstance(classOf[ITUI])
    val gui = injector.getInstance(classOf[IMinesweeperGUI])

    gui.visible = true // Ensure GUI is visible
    tui.run()
  }
}
