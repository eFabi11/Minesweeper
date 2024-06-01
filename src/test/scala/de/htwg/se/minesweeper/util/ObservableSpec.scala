import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.util.{Observable, Observer}

class ObservableSpec extends AnyFlatSpec with Matchers {

  "An Observable" should "notify observers correctly" in {
    class TestObservable extends Observable {
      var updated = false
      def updateObservers(): Unit = notifyObservers
    }

    val observable = new TestObservable
    val observer = new Observer {
      override def update: Unit = observable.updated = true
    }

    observable.add(observer)
    observable.updateObservers()
    observable.updated should be(true)
  }
}
