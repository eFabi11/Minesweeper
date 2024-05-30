import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.difficulty.{EasyDifficulty, DifficultyStrategy}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import org.scalatestplus.mockito.MockitoSugar

class ControllerSpec extends AnyWordSpec with Matchers with MockitoSugar {

  "A Controller" should {
    "initialize the field" in {
      val game = mock[Game]
      val field = mock[Field]
      when(game.gridSize).thenReturn(10)
      doNothing().when(game).setDifficulty()
      // Fügen Sie hier weitere Assertions hinzu, um das Verhalten Ihres Controllers zu überprüfen
    }
  }
}