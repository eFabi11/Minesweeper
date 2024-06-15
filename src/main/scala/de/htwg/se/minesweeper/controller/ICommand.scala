package de.htwg.se.minesweeper.controller

trait ICommand {
    def execute(): Unit
    def undo(): Unit
}
