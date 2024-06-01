import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.minesweeper.util.InputSource

class MockInputSource extends InputSource {
  private var inputs: List[String] = List()
  
  def setInputs(inputs: List[String]): Unit = {
    this.inputs = inputs
  }
  
  override def readLine(): String = {
    if (inputs.nonEmpty) {
      val input = inputs.head
      inputs = inputs.tail
      input
    } else {
      ""
    }
  }
}

class InputSourceSpec extends AnyFlatSpec with Matchers {

  "A MockInputSource" should "return the expected input" in {
    val mockInputSource = new MockInputSource
    mockInputSource.setInputs(List("test input"))
    
    val result = mockInputSource.readLine()
    result should be ("test input")
  }
  
  it should "return empty string if no inputs are set" in {
    val mockInputSource = new MockInputSource
    
    val result = mockInputSource.readLine()
    result should be ("")
  }
}
