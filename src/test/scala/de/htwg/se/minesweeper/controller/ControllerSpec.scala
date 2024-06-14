package de.htwg.se.minesweeper.controller

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty._
import de.htwg.se.minesweeper.command.{UncoverCommand, FlagCommand}
import de.htwg.se.minesweeper.util.FileIOInterface

class ControllerSpec extends AnyFlatSpec with Matchers with MockitoSugar {

  "A Controller" should "set the difficulty correctly" in {
    val field = mock[Field]
    val game = mock[Game]
    val fileIO = mock[FileIOInterface]
    val controller = new Controller(field, game, fileIO)
    val strategy = mock[DifficultyStrategy]

    controller.setDifficulty(strategy)

    verify(game, times(1)).setDifficultyStrategy(strategy)
    verify(game, times(1)).setDifficulty()
  }

  it should "uncover a field correctly" in {
    val field = mock[Field]
    val game = mock[Game]
    val fileIO = mock[FileIOInterface]
    val controller = spy(new Controller(field, game, fileIO))

    doNothing().when(controller).executeCommand(any[UncoverCommand])

    controller.uncoverField(1, 1)

    verify(controller, times(1)).executeCommand(any[UncoverCommand])
  }

  it should "flag a field correctly" in {
    val field = mock[Field]
    val game = mock[Game]
    val fileIO = mock[FileIOInterface]
    val controller = spy(new Controller(field, game, fileIO))

    doNothing().when(controller).executeCommand(any[FlagCommand])

    controller.flagField(1, 1)

    verify(controller, times(1)).executeCommand(any[FlagCommand])
  }

  it should "execute and undo commands correctly" in {
    val field = new Field(3, Symbols.Covered)
    val game = new Game()
    val fileIO = mock[FileIOInterface]
    val controller = new Controller(field, game, fileIO)
    val command = mock[UncoverCommand]

    controller.executeCommand(command)
    verify(command, times(1)).execute()

    controller.undo()
    verify(command, times(1)).undo()
  }

  it should "restart the game correctly" in {
    val field = new Field(3, Symbols.Covered)
    val game = mock[Game]
    val fileIO = mock[FileIOInterface]
    val controller = spy(new Controller(field, game, fileIO))

    controller.restart()

    verify(game, times(1)).gameState = Status.Playing
    verify(controller, times(1)).initializeField()
  }

  it should "save the game correctly" in {
    val field = new Field(3, Symbols.Covered)
    val game = mock[Game]
    val fileIO = mock[FileIOInterface]
    val controller = new Controller(field, game, fileIO)

    controller.save()

    verify(fileIO, times(1)).save(field)
  }

  it should "load the game correctly" in {
    val field = new Field(3, Symbols.Covered)
    val game = mock[Game]
    val fileIO = mock[FileIOInterface]
    val controller = new Controller(field, game, fileIO)

    when(fileIO.load()).thenReturn(field)

    controller.load()

    verify(fileIO, times(1)).load()
    controller.field should be(field)
  }
}
