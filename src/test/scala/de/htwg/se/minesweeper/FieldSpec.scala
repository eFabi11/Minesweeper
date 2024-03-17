package de.htwg.se.minesweeper.model


import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec 
{
    "A minesweeper Field" when 
    {
        "is empty" should 
        {
            val fieldOne = new Field(1, Symbols.Empty)
            val field2 = new Field(2, Symbols.Empty)
            val field3 = new Field(3, Symbols.Empty)

            val endl = sys.props("line.separator")
            "have a bar as String of form '+---+---+---+'" in 
            {
                field3.bar() should be("+---+---+---+" + endl)
            }
            "also have a bar as String of form '+---+---+---+'" in 
            {
                field2.bar() should be("+---+---+---+" + endl)
            }
            "still have a bar as String of form '+---+---+---+'" in 
            {
                fieldOne.bar() should be("+---+---+---+" + endl)
            }
            "have a scalable bar" in 
            {
                fieldOne.bar(1,1) should be("+-+" + endl)
                field2.bar(2,1) should be("+--+" + endl)
                field2.bar(1,2) should be("+-+-+" + endl)
                field3.bar(3,3) should be("+---+---+---+" + endl)
            }
            "have cells as String of form '|   |   |   |'" in 
            {
                field3.cells(0) should be ("|   |   |   |" + endl)
            }
            "have scalable cells" in 
            {
                fieldOne.cells(0, 1) should be("| |" + endl)
                field2.cells(0, 1) should be("| | |" + endl)
                field3.cells(2,3) should be("|   |   |   |" + endl)
                fieldOne.cells(0,3) should be("|   |" + endl)
            }
            "have a mesh in the form" + 
            "+-+" + 
            "| |" + 
            "+-+" in 
            {
                fieldOne.mesh(1) should be("+-+" + endl + "| |" + endl + "+-+" + endl)
                field3.mesh(1) should be("+-+-+-+" + endl + "| | | |" + endl + "+-+-+-+" + endl + "| | | |" + endl + "+-+-+-+" + endl + "| | | |" + endl + "+-+-+-+" + endl )
                field2.mesh(1) should be("+-+-+" + endl + "| | |" + endl + "+-+-+" + endl + "| | |" + endl + "+-+-+" + endl)
            }
            "filled with Empty" should 
            {
                val field = new Field(3, Symbols.Empty)
                "be empty initially with Field(Symbols.Empty)" in 
                {
                     field.toString() should be("+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" +endl)
                }
            }

        "generate correct cells representation based on the cell width and content" in {
            val side = 3
            val testFieldEmpty = new Field(side, Symbols.Empty)
            val testFieldBomb = new Field(side, Symbols.Bomb)

            // Test für eine Standardzellbreite mit leerem Inhalt
            testFieldEmpty.cells(0) shouldBe "|   |   |   |" + sys.props("line.separator")

            // Test für eine Standardzellbreite mit Bombeninhalt
            testFieldBomb.cells(0) shouldBe "| * | * | * |" + sys.props("line.separator")

            // Test für eine angepasste Zellbreite mit leerem Inhalt
            val customCellWidth = 5
            testFieldEmpty.cells(0, customCellWidth) shouldBe "|     |     |     |" + sys.props("line.separator")

            // Test für eine angepasste Zellbreite mit Bombeninhalt
            testFieldBomb.cells(0, customCellWidth) shouldBe "|  *  |  *  |  *  |" + sys.props("line.separator")
        }            

        }
    }
}