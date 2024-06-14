package de.htwg.se.minesweeper

import com.google.inject.{Guice, Injector}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Matrix, Symbols}
import de.htwg.se.minesweeper.interfaces.{IController, IField, IGame}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MinesweeperModuleSpec extends AnyFlatSpec with Matchers {

  "A MinesweeperModule" should "bind IGame to an instance of Game" in {
    val injector: Injector = Guice.createInjector(new MinesweeperModule)
    val game: IGame = injector.getInstance(classOf[IGame])
    game shouldBe a [Game]
  }

  it should "bind IController to an instance of Controller" in {
    val injector: Injector = Guice.createInjector(new MinesweeperModule)
    val controller: IController = injector.getInstance(classOf[IController])
    controller shouldBe a [Controller]
  }

  it should "provide a Field with the correct initial state" in {
    val injector: Injector = Guice.createInjector(new MinesweeperModule)
    val field: Field = injector.getInstance(classOf[Field])
    field.size should be (10)
    field.cell(0, 0) should be (Symbols.Covered)
    field.bomben.size should be (10)
    field.bomben.cell(0, 0) should be (Symbols.Empty)
  }
}
