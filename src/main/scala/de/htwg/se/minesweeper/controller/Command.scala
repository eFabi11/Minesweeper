package de.htwg.se.minesweeper.command

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.Field

trait Command {
    def execute(): Unit
    def undo(): Unit
}

class UncoverCommand(controller: Controller, x: Int, y: Int) extends Command {
    private var previousState: Option[Field] = None

    override def execute(): Unit = {
        previousState = Some(controller.field.copy())
        if (controller.isFirstMove) {
            controller.firstMove(x, y)
            controller.isFirstMove = false
        } else {
            controller.field = controller.field.open(x, y, controller.game)
            controller.checkGameState()
        }
        controller.notifyObservers
    }

    override def undo(): Unit = {
        previousState.foreach(state => controller.field = state)
        controller.notifyObservers
    }
}

class FlagCommand(controller: Controller, x: Int, y: Int) extends Command {
    private var previousState: Option[Field] = None

    override def execute(): Unit = {
        previousState = Some(controller.field.copy())
        controller.field = controller.field.flag(x, y)
        controller.checkGameState()
        controller.notifyObservers
    }

    override def undo(): Unit = {
        previousState.foreach(state => controller.field = state)
        controller.notifyObservers
    }
}
