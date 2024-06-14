package de.htwg.se.minesweeper.controller

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty._

class ControllerSpec extends AnyFlatSpec with Matchers with MockitoSugar {


  it should "set the difficulty correctly" in {
    val field = mock[Field]
    val game = mock[Game]
    val controller = new Controller(field, game)
    val strategy = mock[DifficultyStrategy]

    // Action
    controller.setDifficulty(strategy)

    // Verifying interactions
    verify(game, times(1)).setDifficultyStrategy(strategy)
    verify(game, times(1)).setDifficulty()
  }
}
