import scala.io.Source.fromURL

class ASCIIart(filename: String) {

  val (largeur, hauteur, liste) = lectureFichierSource()
  val tableLettre: Map[Char, List[String]] = listeToMap()

  private def lectureFichierSource() = {
    val f = fromURL(getClass.getResource(filename)).getLines()
    val largeur = f.next().toInt
    val longeur = f.next().toInt
    val liste = for (l <- f) yield l
    (largeur, longeur, liste.toList)
  }

  /**
    * Transform the list of ASCII representation to a Map with their alphabet character as a key
    * @return
    */
  private def listeToMap(): Map[Char, List[String]] = {
    /**
      * Ascii representation for one character
      * @param char character to transform
      * @return
      */
    def oneChar(char: Char): List[String] = {
      def rec(line: Int, list: List[String] = List()): List[String] = {
        if (line < 0) list
        else if ('a' <= char && char <= 'z') rec(line - 1, this.liste(line).grouped(this.largeur).toList(char - 97) :: list)
        else rec(line - 1, this.liste(line).grouped(this.largeur).toList('z' + 1 - 97) :: list)

      }

      rec(this.hauteur - 1)
    }

    /**
      * Create a map of ASCII representation of all alphabetic letters, defautlt '?'
      * @return
      */
    def alphabet(): Map[Char, List[String]] = {
      def nextChar(character: Char, map: Map[Char, List[String]]): Map[Char, List[String]] = {
        val lowerChar = character.toLower
        if (lowerChar > 'z') map + ('?' -> oneChar(('z' + 1).toChar))
        else {
          nextChar((lowerChar + 1).toChar, map + (lowerChar -> oneChar(lowerChar)))
        }
      }

      nextChar('a', Map().withDefaultValue(oneChar('?')))
    }

    alphabet()
  }


  def apply(mot: String): String = {
    /**
      * Create one line of ascii string
      *
      * @param str : string to convert in ASCII
      * @param line
      * @param acc
      * @return
      */
    def oneLine(str: String, line: Int = 0, acc: String = ""): String = {
      if (str.isEmpty) acc
      else {
        val start = 0
        val end = this.largeur
        oneLine(str.tail, line, acc + this.tableLettre(str.head)(line).mkString)
      }
    }

    /**
      * Create the ASCII representation of the given string
      *
      * @param str string to convert
      * @param acc
      * @param line
      * @return
      */
    def everyLines(str: String, acc: String = "", line: Int = 0): String = {
      if (line == this.hauteur) acc.reverse.tail.reverse // Delete the last'\n'
      else everyLines(str, acc + oneLine(str, line) + "\n", line + 1)
    }

    everyLines(mot)
  }
}
