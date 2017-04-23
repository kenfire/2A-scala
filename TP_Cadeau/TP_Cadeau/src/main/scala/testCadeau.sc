object testCadeau {
  def remove(num: Int, list: List[Int]): List[Int] = list diff List(num)

  val l = List(50, 15, 35, 15)
  remove(l.min, l)
  l :+ 1

  def equilibre(somme: Int, capacite: List[Int]): List[Int] = {
    val length = capacite.length

    val l = for (i <- 0 until length) yield {
      if (i == length - 1) {
        math.ceil(somme / length.toDouble).toInt
      } else {
        somme / length
      }
    }

    l.toList
  }

  def desequilibre(somme: Int, capacite: List[Int], result: List[Int] = List()): List[Int] = {
    val min = capacite.min
    result :+ min
    if (capacite.length == 1) {
      result
    } else {
      desequilibre(somme - min, remove(min, capacite), result)
    }
  }


  def repartition(somme: Int, capacite: List[Int]): Option[List[Int]] = {
    val avg = somme.toDouble / capacite.length
    if (capacite.sum < somme) {
      None
    } else if (capacite.min > avg) {
      Option(equilibre(somme, capacite))
    } else {
      Option(desequilibre(somme, capacite))
    }
  }

  //  repartition(30, List(20, 20, 20))
  repartition(100, List(100, 1, 60)).get
  equilibre(100, List(100, 1, 60))
  repartition(100, List(15, 35, 50)).get
}