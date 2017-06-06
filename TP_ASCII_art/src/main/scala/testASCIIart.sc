object testASCIIart {

  val ascii = new ASCIIart("type1.txt")

  def oneLine(str: String, line: Int = 0, acc: String = ""): String = {
    if (str.isEmpty) acc
    else {
      val start = 0
      val end = ascii.largeur
      oneLine(str.tail, line, acc + ascii.tableLettre(str.head)(line).mkString)
    }
  }

  def everyLines(str: String, acc: String = "", line: Int = 0): String = {
    if (line == ascii.hauteur) acc
    else everyLines(str, acc + oneLine(str, line) + "\n", line + 1)
  }

  println(oneLine("scala"))
  //  ascii.tableLettre('s')
  //  ascii.tableLettre('s')(0).mkString + ascii.tableLettre('c')(0).mkString
  //  ascii.tableLettre('s')(1).mkString + ascii.tableLettre('c')(1).mkString
  //  ascii.tableLettre('s')(2).mkString + ascii.tableLettre('c')(2).mkString
  //  ascii.tableLettre('s')(3).mkString + ascii.tableLettre('c')(3).mkString
  //  ascii.tableLettre('s')(4).mkString + ascii.tableLettre('c')(4).mkString

  println(everyLines("scala"))
  println("REP =\n ##  ##  #  #    #  \n#   #   # # #   # # \n #  #   ### #   ### \n  # #   # # #   # # \n##   ## # # ### # # ")
}