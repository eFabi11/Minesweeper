package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.controller.Controller
import de.htwg.se.minesweeper.util.{Observer, FileIOInterface}
import de.htwg.se.minesweeper.model.{Symbols, Status}
import de.htwg.se.minesweeper.model.field.Field
import scala.swing._
import scala.swing.event._
import java.awt.event.{MouseEvent => AwtMouseEvent}
import de.htwg.se.minesweeper.interfaces.IMinesweeperGUI

class MinesweeperGUI(controller: Controller, fileIO: FileIOInterface) extends Frame with IMinesweeperGUI with Observer {
  controller.add(this)
  title = "Minesweeper"
  preferredSize = new Dimension(800, 600)

  var gridPanel: GridPanel = new GridPanel(controller.field.size, controller.field.size) {
    preferredSize = new Dimension(600, 600)
  }

  val difficultyComboBox: ComboBox[String] = new ComboBox(Seq("Easy", "Medium", "Hard"))
  val flagLabel: Label = new Label("Flags: 0")
  val timeLabel: Label = new Label("Time: 0")
  val undoButton: Button = new Button("Undo")
  val restartButton: Button = new Button("Restart")
  val exitButton: Button = new Button("Exit")
  val saveButton: Button = new Button("Save State")
  val loadButton: Button = new Button("Load State")

  val topPanel: FlowPanel = new FlowPanel {
    contents += difficultyComboBox
    contents += flagLabel
    contents += timeLabel
    contents += undoButton
    contents += restartButton
    contents += saveButton
    contents += loadButton
    contents += exitButton
  }

  val mainPanel: BorderPanel = new BorderPanel {
    layout(topPanel) = BorderPanel.Position.North
    layout(gridPanel) = BorderPanel.Position.Center
  }

  contents = mainPanel

  listenTo(difficultyComboBox.selection, undoButton, restartButton, exitButton, saveButton, loadButton)
  reactions += {
    case SelectionChanged(`difficultyComboBox`) =>
      updateDifficulty()
    case ButtonClicked(`undoButton`) =>
      controller.undo()
    case ButtonClicked(`restartButton`) =>
      controller.restart()
    case ButtonClicked(`exitButton`) =>
      sys.exit(0)
    case ButtonClicked(`saveButton`) =>
      fileIO.save(controller.field)
      Dialog.showMessage(this, "Game state saved.", title = "Save State")
    case ButtonClicked(`loadButton`) =>
      controller.setField(fileIO.load)
      refreshGrid()
      Dialog.showMessage(this, "Game state loaded.", title = "Load State")
  }

  def updateDifficulty(): Unit = {
    val difficulty = difficultyComboBox.selection.item
    difficulty match {
      case "Easy" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.EasyDifficulty)
      case "Medium" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.MediumDifficulty)
      case "Hard" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.HardDifficulty)
    }
  }

  def refreshGrid(): Unit = {
    if (controller.field.size > 0) {
      gridPanel = new GridPanel(controller.field.size, controller.field.size) {
        preferredSize = new Dimension(600, 600)
      }

      for {
        row <- 0 until controller.field.size
        col <- 0 until controller.field.size
      } {
        val button = new Button {
          reactions += {
            case ButtonClicked(_) =>
              controller.uncoverField(col, row)
          }
          listenTo(mouse.clicks)
          reactions += {
            case e: MousePressed if (e.peer.getButton == AwtMouseEvent.BUTTON3) =>
              controller.flagField(col, row)
          }
        }

        controller.field.cell(row, col) match {
          case Symbols.Covered => button.background = java.awt.Color.GREEN
          case Symbols.Flag => button.background = java.awt.Color.BLUE
          case Symbols.Empty => button.background = java.awt.Color.GRAY
          case Symbols.Bomb =>
            if (controller.field.cell(row, col) == Symbols.Bomb && controller.game.gameState == Status.Lost) {
              button.background = java.awt.Color.RED
            } else {
              button.background = java.awt.Color.BLACK
            }
          case _ =>
            button.background = java.awt.Color.LIGHT_GRAY
            button.text = controller.field.cell(row, col).toString
        }

        gridPanel.contents += button
      }

      mainPanel.layout(gridPanel) = BorderPanel.Position.Center
      mainPanel.revalidate()
      mainPanel.repaint()
    }
  }

  def showEndGameDialog(status: Status): Unit = {
    val message = status match {
      case Status.Won => "Congratulations, you won!"
      case Status.Lost => "Sorry, you lost!"
      case _ => ""
    }
    Dialog.showMessage(this, message, title = "Game Over", Dialog.Message.Info)
  }

  override def update(): Unit = {
    refreshGrid()
    flagLabel.text = s"Flags: ${controller.field.matrix.rows.flatten.count(_ == Symbols.Flag)}"
    if (controller.game.gameState == Status.Won || controller.game.gameState == Status.Lost) {
      showEndGameDialog(controller.game.gameState)
    }
  }

  refreshGrid()
  open()
}
