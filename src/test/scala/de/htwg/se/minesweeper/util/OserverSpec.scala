package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObservableSpec extends AnyWordSpec with Matchers {

  "An Observable" should {
    "initialize subscribers correctly" in {
      val observable = new Observable {}
      observable.subscribers shouldBe empty
    }

    "add and notify observers" in {
      val observable = new Observable {}
      var observer1Updated = false
      val observer1 = new Observer {
        override def update(): Unit = observer1Updated = true
      }
      var observer2Updated = false
      val observer2 = new Observer {
        override def update(): Unit = observer2Updated = true
      }

      observable.add(observer1)
      observable.add(observer2)

      observable.notifyObservers()

      observer1Updated should be(true)
      observer2Updated should be(true)
    }

    "remove observers" in {
      val observable = new Observable {}
      var observer1Updated = false
      val observer1 = new Observer {
        override def update(): Unit = observer1Updated = true
      }
      var observer2Updated = false
      val observer2 = new Observer {
        override def update(): Unit = observer2Updated = true
      }

      observable.add(observer1)
      observable.add(observer2)
      observable.remove(observer1)

      observable.notifyObservers()

      observer1Updated should be(false)
      observer2Updated should be(true)
    }
  }
}
