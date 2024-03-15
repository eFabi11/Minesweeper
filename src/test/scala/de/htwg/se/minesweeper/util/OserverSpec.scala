package de.htwg.se.minesweeper.util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._

class ObservableSpec extends AnyWordSpec with Matchers with MockitoSugar {

  "An Observable" when {
    "no observers are added" should {
      "not notify any observers" in {
        val observable = new Observable {}
        val observer = mock[Observer]

        observable.notifyObservers

        verify(observer, never()).update
      }
    }

    "one observer is added" should {
      "notify the observer" in {
        val observable = new Observable {}
        val observer = mock[Observer]

        observable.add(observer)
        observable.notifyObservers

        verify(observer, times(1)).update
      }
    }

    "an observer is added and then removed" should {
      "not notify the removed observer" in {
        val observable = new Observable {}
        val observer = mock[Observer]

        observable.add(observer)
        observable.remove(observer)
        observable.notifyObservers

        verify(observer, never()).update
      }
    }

    "multiple observers are added" should {
      "notify all observers" in {
        val observable = new Observable {}
        val observer1 = mock[Observer]
        val observer2 = mock[Observer]

        observable.add(observer1)
        observable.add(observer2)
        observable.notifyObservers

        verify(observer1, times(1)).update
        verify(observer2, times(1)).update
      }
    }
  }
}
