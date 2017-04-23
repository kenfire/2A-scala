class Conway(init: Int) {
  def apply(rang: Int): String = {
    /**
      * Count the number of consecutive characters
      *
      * @param str      the string to count from
      * @param previous the character to count
      * @param i        the number of consecutive character
      * @return The character to count and the amount
      */
    def consecutiveChar(str: String, previous: Char, i: Int = 0): (Char, Int) = {
      if (previous == str.charAt(0) && str.tail == "") {
        (previous, i + 1)
      }
      else if (previous == str.charAt(0) && str.tail != "") {
        consecutiveChar(str.tail, previous, i + 1)
      } else
        (previous, i)
    }

    /**
      * Generate the next Conway line
      *
      * @param str the previous rank string
      * @param result
      * @return
      */
    def nextLine(str: String, result: String = ""): String = {
      val tuple = consecutiveChar(str, str.charAt(0))
      val resultPart = result + " " + tuple._2 + " " + tuple._1
      val tail = str.splitAt(tuple._2)._2

      if (tail == "") {
        result + " " + tuple._2 + " " + tuple._1
      } else {
        nextLine(tail, resultPart)
      }
    }

    /**
      * Recursion on the number of line
      *
      * @param rang
      * @param i
      * @param result
      * @return
      */
    def rec(rang: Int, i: Int = 1, result: String = " " + init): String = {
      if (i == rang) result.tail
      else {
        rec(rang, i + 1, nextLine(result.filterNot((x: Char) => x.isWhitespace)))
      }
    }

    rec(rang)
  }
}
