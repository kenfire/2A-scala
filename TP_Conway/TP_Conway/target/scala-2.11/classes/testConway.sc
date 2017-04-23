
object testConway {
  def consecutiveChar(str: String, previous: Char, i: Int = 0): (Char, Int) = {
    if (previous == str.charAt(0) && str.tail == "") {
      (previous, i + 1)
    }
    else if (previous == str.charAt(0) && str.tail != "") {
      consecutiveChar(str.tail, previous, i + 1)
    } else
      (previous, i)
  }


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


  def rec(rang: Int, i: Int = 1, result: String = "1"): String = {
    println(result)

    if (i == rang) result
    else {
      rec(rang, i + 1, nextLine(result.filterNot((x: Char) => x.isWhitespace)))
    }
  }

  val c = new Conway(1)
  //  c(2)
  val str = "1"
  val str2 = "11"
  //  val str = "3"

  nextLine(str)
  nextLine(str2)
  rec(1)
  rec(2)
  rec(3)
  //  val str2 = "3"
  //  str2.charAt(0)
  //  str2.tail
  //  "".length
  //  consecutiveChar(str2, str2.charAt(0))

}