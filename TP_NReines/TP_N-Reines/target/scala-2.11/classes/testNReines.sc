object testNReines {

  val quatreReines = new NReines(4)
  //quatreReines.solutions
  //quatreReines.nombreSolutions
  //quatreReines.affiche(0)

  val l = List(1)
  l.contains(2)
  l.contains(1)

  val r4 = new NReines(4)
  r4.diagonal(1, List(0, 2))
  r4.diagonal(2, List(0, 2))


  r4.solution(List(0))
  r4.solution(List(1))

  r4.estOk(3, List(1))
}