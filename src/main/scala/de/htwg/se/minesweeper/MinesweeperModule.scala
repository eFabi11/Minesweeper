package de.htwg.se.minesweeper

import com.google.inject.{AbstractModule, Provides}
import de.htwg.se.minesweeper.controller.controller.Controller
import de.htwg.se.minesweeper.interfaces.{IMinesweeperGUI, ITUI}
import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.model.field.Field
import de.htwg.se.minesweeper.model.IGame
import de.htwg.se.minesweeper.model.game.Game
import de.htwg.se.minesweeper.util.{FileIOInterface, InputSource, StdInInputSource, FileIOJSON}
import de.htwg.se.minesweeper.aview.{MinesweeperGUI, TUI}
import de.htwg.se.minesweeper.model.Symbols

class MinesweeperModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[IController]).to(classOf[Controller])
    bind(classOf[IGame]).to(classOf[Game])
    bind(classOf[Field]).toInstance(new Field(10, Symbols.Covered)) // Default field, adjust as necessary
    bind(classOf[InputSource]).toInstance(StdInInputSource)
    bind(classOf[ITUI]).to(classOf[TUI])
    bind(classOf[FileIOInterface]).to(classOf[FileIOJSON]) // Use FileIOJSON or FileIOXML
  }

  @Provides
  def provideMinesweeperGUI(controller: Controller, fileIO: FileIOInterface): IMinesweeperGUI = {
    new MinesweeperGUI(controller, fileIO)
  }
}