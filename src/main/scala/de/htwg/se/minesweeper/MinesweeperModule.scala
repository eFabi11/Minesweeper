package de.htwg.se.minesweeper

import com.google.inject.{AbstractModule, Provides}
import de.htwg.se.minesweeper.controller.controller.Controller
import de.htwg.se.minesweeper.interfaces.{IMinesweeperGUI, ITUI}
import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.model.field.Field
import de.htwg.se.minesweeper.model.IGame
import de.htwg.se.minesweeper.model.game.Game
import de.htwg.se.minesweeper.util.{FileIOInterface, FileIOJSON, FileIOXML, InputSource, StdInInputSource}
import de.htwg.se.minesweeper.aview.{MinesweeperGUI, TUI}
import de.htwg.se.minesweeper.model.Symbols

class MinesweeperModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[IController]).to(classOf[Controller])
    bind(classOf[IGame]).to(classOf[Game])
    bind(classOf[Field]).toInstance(new Field(10, Symbols.Covered)) // Default field, adjust as necessary
    bind(classOf[InputSource]).toInstance(StdInInputSource)
    bind(classOf[ITUI]).to(classOf[TUI])
  }

  @Provides
  def provideMinesweeperGUI(controller: Controller, fileIOJSON: FileIOJSON, fileIOXML: FileIOXML): IMinesweeperGUI = {
    new MinesweeperGUI(controller, fileIOJSON, fileIOXML)
  }

  @Provides
  def provideFileIOJSON(): FileIOJSON = new FileIOJSON

  @Provides
  def provideFileIOXML(): FileIOXML = new FileIOXML
}
