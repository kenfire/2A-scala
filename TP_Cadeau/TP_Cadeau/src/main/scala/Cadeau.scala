object Cadeau {
  /**
    * Répatition equilibre avec la derniere personne payant le reste en plus
    * @param somme
    * @param capacite
    * @return
    */
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

  /**
    * Supprime un element de la list
    * @param num le numero de l'element a supprime
    * @param list
    * @return
    */
  def remove(num: Int, list: List[Int]): List[Int] = list diff List(num)

  /**
    * Répartition désequilibre, la plus petite capacite est traité en premier
    * @param somme
    * @param capacite
    * @param result
    * @return
    */
  def desequilibre(somme: Int, capacite: List[Int], result: List[Int] = List()): List[Int] = {
    val min = capacite.min

    if (capacite.length == 1) {
      result :+ min
    } else {
      repartition(somme - min, remove(min, capacite)).get :+ min
    }
  }


  /**
    * Fonction de répartition de la somme en fonction des capacites
    * @param somme
    * @param capacite
    * @return
    */
  def repartition(somme: Int, capacite: List[Int]): Option[List[Int]] = {
    val avg = (somme.toDouble / capacite.length).floor
    if (capacite.sum < somme) {
      None
    } else if (capacite.min >= avg) {
      Option(equilibre(somme, capacite).sorted)
    } else {
      Option(desequilibre(somme, capacite).sorted)
    }
  }
}
