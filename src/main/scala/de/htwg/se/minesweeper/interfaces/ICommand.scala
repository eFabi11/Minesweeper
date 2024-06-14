package de.htwg.se.minesweeper.interfaces

trait ICommand {
    def execute(): Unit
    def undo(): Unit
}
