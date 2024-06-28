package de.htwg.se.minesweeper.template

import de.htwg.se.minesweeper.model.Game.Game
import de.htwg.se.minesweeper.model.IField
import de.htwg.se.minesweeper.model.Field.Field

abstract class GameInitializer {
  def initializeGame(game: Game, x: Int, y: Int): IField = {
    setDifficulty(game)
    initializeField(game, x, y)
  }

  protected def setDifficulty(game: Game): Unit

  protected def initializeField(game: Game, x: Int, y: Int): IField
}

class EasyGameInitializer extends GameInitializer {
  override protected def setDifficulty(game: Game): Unit = {
    game.gridSize = 3
    game.bombCount = 1
  }

  override protected def initializeField(game: Game, x: Int, y: Int): IField = {
    game.initializeField(x, y)
  }
}

class MediumGameInitializer extends GameInitializer {
  override protected def setDifficulty(game: Game): Unit = {
    game.gridSize = 9
    game.bombCount = 6
  }

  override protected def initializeField(game: Game, x: Int, y: Int): IField = {
    game.initializeField(x, y)
  }
}

class HardGameInitializer extends GameInitializer {
  override protected def setDifficulty(game: Game): Unit = {
    game.gridSize = 16
    game.bombCount = 40
  }

  override protected def initializeField(game: Game, x: Int, y: Int): IField = {
    game.initializeField(x, y)
  }
}
