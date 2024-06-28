import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.minesweeper.controller.{IController, Controller}
import de.htwg.se.minesweeper.model.Field.Field
import de.htwg.se.minesweeper.model.Game.Game
import de.htwg.se.minesweeper.util.{FileIOInterface, FileIOJSON}

class MinesweeperModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind(classOf[Field]).toInstance(new Field(new Matrix(10, Symbols.Covered), new Matrix(10, Symbols.Empty)))
    bind(classOf[Game]).toInstance(new Game())
    bind(classOf[IController]).to(classOf[Controller])
    bind(classOf[FileIOInterface]).to(classOf[FileIOJSON])
  }
}
