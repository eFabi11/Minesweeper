package de.htwg.se.minesweeper.aview

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty._
import de.htwg.se.minesweeper.util.MockInputSource

class TUISpec extends AnyFlatSpec with Matchers with MockitoSugar {

  "A TUI" should "update the view correctly" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List())
    val tui = new TUI(controller, inputSource)
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.update
    verify(controller).field
  }

  it should "select difficulty correctly" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("E"))
    val tui = new TUI(controller, inputSource)
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.selectDifficulty()
    verify(controller).setDifficulty(isA(classOf[EasyDifficulty]))
  }

  it should "handle medium difficulty selection" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("M"))
    val tui = new TUI(controller, inputSource)
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.selectDifficulty()
    verify(controller).setDifficulty(isA(classOf[MediumDifficulty]))
  }

  it should "handle hard difficulty selection" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("H"))
    val tui = new TUI(controller, inputSource)
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.selectDifficulty()
    verify(controller).setDifficulty(isA(classOf[HardDifficulty]))
  }

  it should "handle invalid difficulty selection by defaulting to easy" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("X"))
    val tui = new TUI(controller, inputSource)
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.selectDifficulty()
    verify(controller).setDifficulty(isA(classOf[EasyDifficulty]))
  }

  it should "handle user input and move correctly" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("o00"))
    val tui = new TUI(controller, inputSource)
    when(controller.game).thenReturn(mock[Game])
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.parseInputAndPrintLoop(List("o00"))
    verify(controller).uncoverField(0, 0)
  }

  it should "handle flag command correctly" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("f00"))
    val tui = new TUI(controller, inputSource)
    when(controller.game).thenReturn(mock[Game])
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.parseInputAndPrintLoop(List("f00"))
    verify(controller).flagField(0, 0)
  }

  it should "handle undo command correctly" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("u"))
    val tui = new TUI(controller, inputSource)
    when(controller.game).thenReturn(mock[Game])
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.parseInputAndPrintLoop(List("u"))
    verify(controller).undo()
  }

  it should "exit on quit command" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("q"))
    val tui = new TUI(controller, inputSource)
    when(controller.game).thenReturn(mock[Game])
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    noException should be thrownBy tui.parseInputAndPrintLoop(List("q"))
  }

  it should "handle valid user input using parseInputAndPrintLoop" in {
    val controller = mock[Controller]
    val inputSource = new MockInputSource(List("o00"))
    val tui = new TUI(controller, inputSource)
    when(controller.game).thenReturn(mock[Game])
    when(controller.field).thenReturn(new Field(3, Symbols.Covered))
    tui.parseInputAndPrintLoop(List("o00"))
    verify(controller).uncoverField(0, 0)
  }


}
