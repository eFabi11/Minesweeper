package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.model.{Field, Matrix, Symbols, Game, Status}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.util.StdInInputSource
import de.htwg.se.minesweeper.difficulty.DifficultyLevel

object Minesweeper {
    def main(args: Array[String]): Unit = {
        val msGame = new Game(DifficultyLevel.Easy)
        val gridSize = 10 // Oder eine andere Logik, um die Größe basierend auf der Schwierigkeitsstufe zu bestimmen
        val controller = new Controller(new Field(gridSize, Symbols.Covered), msGame) // Korrekt
        val inputSource = StdInInputSource
        val tui = new TUI(controller, inputSource)
        
        // Erste Zugkoordinaten vom Benutzer holen
        val (startX, startY) = tui.getFirstMoveCoordinates()
        
        // Spielfeld mit den Koordinaten des ersten Zuges initialisieren
        val coveredField = msGame.initializeField(startX, startY)
        
        // Controller und TUI mit dem aktualisierten Feld initialisieren
        controller.field = coveredField
        
        tui.run()
    }
}