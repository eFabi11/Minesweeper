package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObservableSpec extends AnyWordSpec with Matchers {
  "An Observable" when {
    "new" should {
      "have no subscribers" in {
        val observable = new Observable {}
        observable.subscribers should be(Vector.empty)
      }
    }

    "an observer is added" should {
      "contain the observer in the subscribers list" in {
        val observable = new Observable {}
        val observer = new Observer {
          override def update: Unit = {}
        }
        observable.add(observer)
        observable.subscribers should contain(observer)
      }
    }

    "an observer is removed" should {
      "not contain the observer in the subscribers list" in {
        val observable = new Observable {}
        val observer = new Observer {
          override def update: Unit = {}
        }
        observable.add(observer)
        observable.remove(observer)
        observable.subscribers should not contain(observer)
      }
    }

    "observers are notified" should {
      "call update on each observer" in {
        val observable = new Observable {}
        var updateCount = 0
        val observer1 = new Observer {
          override def update: Unit = { updateCount += 1 }
        }
        val observer2 = new Observer {
          override def update: Unit = { updateCount += 1 }
        }

        observable.add(observer1)
        observable.add(observer2)
        observable.notifyObservers

        updateCount should be(2)
      }
    }
  }
}
