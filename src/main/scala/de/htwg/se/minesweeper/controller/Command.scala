package de.htwg.se.minesweeper.command

import de.htwg.se.minesweeper.interfaces.{IController, ICommand}
import de.htwg.se.minesweeper.model.{Field, Game}

trait Command extends ICommand {
  def execute(): Unit
  def undo(): Unit
}

class UncoverCommand(controller: IController, x: Int, y: Int) extends Command {
  private var previousState: Option[Field] = None

  override def execute(): Unit = {
    previousState = Some(controller.field.copy())
    if (controller.isFirstMove) {
      controller.firstMove(x, y)
      controller.setFirstMove(false)
    } else {
      val newField = controller.field.open(x, y, controller.game.asInstanceOf[Game])
      controller.setField(newField)
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
    previousState = Some(controller.field.copy())
    val newField = controller.field.flag(x, y)
    controller.setField(newField)
    controller.checkGameState()
    controller.notifyObservers()
  }

  override def undo(): Unit = {
    previousState.foreach(state => controller.setField(state))
    controller.notifyObservers()
  }
}
