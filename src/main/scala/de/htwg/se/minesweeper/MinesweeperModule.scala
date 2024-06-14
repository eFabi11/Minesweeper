package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Matrix, Symbols}
import de.htwg.se.minesweeper.interfaces.{IController, IField, IGame}
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
