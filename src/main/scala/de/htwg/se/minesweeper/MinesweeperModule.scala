package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import de.htwg.se.minesweeper.controller.Controller.Controller
import de.htwg.se.minesweeper.model.Matrix
import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.Field.Field
import de.htwg.se.minesweeper.model.Game.Game
import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.model.IGame
import de.htwg.se.minesweeper.util.{FileIOInterface, FileIOJSON, FileIOXML}

class MinesweeperModule extends AbstractModule {
  override def configure(): Unit = {
    // Bind the game and field instances
    bind(classOf[IGame]).to(classOf[Game])
    bind(classOf[Field]).toInstance(new Field(new Matrix(10, Symbols.Covered), new Matrix(10, Symbols.Empty)))

    // Bind the controller
    bind(classOf[IController]).to(classOf[Controller])

    // Bind FileIO implementations
    bind(classOf[FileIOInterface]).to(classOf[FileIOJSON]) // or FileIOXML
  }
}
