package de.htwg.se.minesweeper.util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._

class ObservableSpec extends AnyWordSpec with Matchers with MockitoSugar {

  "An Observable" should {
    "allow observers to be added" in {
      val observable = new Observable {}
      val observer = mock[Observer]

      observable.add(observer)
      observable.subscribers should contain(observer)
    }

    "allow observers to be removed" in {
      val observable = new Observable {}
      val observer = mock[Observer]

      observable.add(observer)
      observable.remove(observer)
      observable.subscribers should not contain observer
    }

    "notify all observers when notifyObservers is called" in {
      val observable = new Observable {}
      val observer1 = mock[Observer]
      val observer2 = mock[Observer]

      observable.add(observer1)
      observable.add(observer2)
      observable.notifyObservers

      verify(observer1).update
      verify(observer2).update
    }
  }
}
