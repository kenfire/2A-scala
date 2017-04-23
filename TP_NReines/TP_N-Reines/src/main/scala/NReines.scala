

class NReines(val n: Int) {

  // Question 1 :
  // étant donnée la position des reines précedentes, renvoye vrai si la colonne est compatible
  def estOk(col: Int, reines: List[Int]): Boolean = {
    !reines.contains(col) && math.abs(reines.head - col) > 1 && diagonal(col, reines)
  }

  //  def diagonal(col: Int, reines: List[Int]): Boolean = {
  //    val line = n - reines.length - 1
  //    if (reines.isEmpty) true
  //    else if ((col - line == reines.head) || (col + line == reines.head)) false
  //    else diagonal(col, reines.tail)
  //  }

  def diagonal(col: Int, reines: List[Int]): Boolean = {
    def rec(col: Int, line: Int, reines: List[Int]): Boolean = {
      if (reines.isEmpty) true
      else if (col - reines.head == line - n - reines.length) false
      else rec(col, line, reines.tail)
    }

    rec(col, reines.length, reines)
  }

  // Question 2
  // calcule la liste des solutions
  lazy val solutions: List[List[Int]] = {
    def rec(reines: List[List[Int]], i: Int = 0): List[List[Int]] = {
      if (i < n) {
        if (solution(List(i)).isDefined) {
          rec(solution(List(i)).get :: reines, i + 1) // ajout de la solution
        } else {
          rec(reines, i + 1)
        }
      } else {
        reines
      }
    }

    rec(List())
  }

  /**
    * Calcul d'une solution hypoyhetique
    *
    * @param reines
    * @param col
    * @return
    */
  def solution(reines: List[Int], col: Int = 0): Option[List[Int]] = {
    if (col >= n) {
      None
    } else if (col < n && reines.length == n) {
      Option(reines)
    } else if (estOk(col, reines)) {
      solution(col :: reines, 0)
    } else {
      solution(reines, col + 1)
    }
  }

  // Question 3
  // Retourne le nombre de solutions
  lazy val nombreSolutions = ???

  // question 4
  // affiche, si elle existe, la i-ème solution
  def affiche(i: Int) = ???

}
