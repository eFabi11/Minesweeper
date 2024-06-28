package de.htwg.se.minesweeper.controller.Command

import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.model.Field.Field
import de.htwg.se.minesweeper.model.Game.Game
import de.htwg.se.minesweeper.controller.ICommand

trait Command extends ICommand {
  def execute(): Unit
  def undo(): Unit
}

class UncoverCommand(controller: IController, x: Int, y: Int) extends Command {
  private var previousState: Option[Field] = None

  override def execute(): Unit = {
    previousState = Some(controller.field.asInstanceOf[Field].copy())
    if (controller.isFirstMove) {
      controller.firstMove(x, y)
      controller.setFirstMove(false)
    } else {
      val newField = controller.field.open(x, y, controller.game.asInstanceOf[Game])
      controller.setField(newField.asInstanceOf[Field])
      controller.checkGameState()
    }
    controller.notifyObservers()
  }

  override def undo(): Unit = {
    previousState.foreach(state => controller.setField(state))
    controller.notifyObservers()
  }
}

class FlagCommand(controller: IController, x: Int, y: Int) extends Command {
  private var previousState: Option[Field] = None

  override def execute(): Unit = {
    previousState = Some(controller.field.asInstanceOf[Field].copy())
    val newField = controller.field.flag(x, y)
    controller.setField(newField.asInstanceOf[Field])
    controller.checkGameState()
    controller.notifyObservers()
  }

  override def undo(): Unit = {
    previousState.foreach(state => controller.setField(state))
    controller.notifyObservers()
  }
}
