package de.htwg.se.minesweeper.util

trait Observer {
  def update(): Unit
}

trait Observable {
  var subscribers: Vector[Observer] = Vector()

  def addObserver(s: Observer): Unit = subscribers = subscribers :+ s
  def removeObserver(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)
  def notifyObservers(): Unit = subscribers.foreach(o => o.update())
}
