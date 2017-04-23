object Monnaie {

  /**
    * Compte le nombre de possibilite de répartition du montant en fonction de la monnaie disponible
    * @param montant
    * @param disponible
    * @return
    */
  def comptePossibilite(montant: Int, disponible: List[Int]): Int = {
    if (montant == 0) 1
    else if (disponible.isEmpty || montant < 0) 0
    else comptePossibilite(montant - disponible.head, disponible) + comptePossibilite(montant, disponible.tail)
  }

  /**
    * Operation sur les tuples
    * @param t
    */
  implicit class TuppleAdd(t: (Int, Int, Int)) {
    def +(p: (Int, Int, Int)) = (t._1 + p._1, t._2 + p._2, t._3 + p._3)

    def -(p: (Int, Int, Int)) = (t._1 - p._1, t._2 - p._2, t._3 - p._3)
  }


  def distributionOptimale(montant: Int, disponible: List[Int]): (Int, Int, Int) = {
    require(disponible.length == 3)

    def rec(montant: Int, disponible: List[Int], t: (Int, Int, Int)): (Int, Int, Int) = {
      val tuple =
        if (montant - disponible.head < 0) // Moins de 10 €
          t + (0, 0, 0)
        else if (montant - disponible.head - disponible(1) < 0) // Moins de 20 €
          t + (1, 0, 0)
        else if (montant - disponible.head - disponible(1) - disponible(2) < 0) // Moins de 50 €
          t + (1, 1, 0)
        else t + (1, 1, 1) // Plus de 50 €

      val tmp = tuple - t
      if (tmp != (0, 0, 0)) {
        rec(montant - tmp._1 * disponible.head - tmp._2 * disponible(1) - tmp._3 * disponible(2), disponible, tuple)
      }
      else if (montant == 0) {
        tuple
      }
      else {
        (0, 0, 0)
      }
    }

    rec(montant, disponible, (0, 0, 0))
  }
}
