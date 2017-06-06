import Logique._

object testLogique {
  val e1 = Equivalent(Proposition("R"), Et(Proposition("P"), Et(Proposition("A"), Proposition("S"))))
  val e2 = Proposition("P")
  val e3 = Non(Proposition("R"))
  val th = Ou(Non(Proposition("A")), Non(Proposition("S")))
  //  val t = new Tableau(List(e2, e3), th)


  val i1 = Map("A" -> true, "P" -> true, "R" -> false, "S" -> false)
  val i2 = Map("A" -> false, "P" -> false, "R" -> true, "S" -> true)
  val i3 = Map("A" -> true, "P" -> false, "R" -> false, "S" -> true)

  evaluation(Et(Non(Constante(true)), Non(Constante(true))), i1)
  evaluation(Et(Non(Constante(false)), Non(Constante(true))), i1)
  evaluation(Et(Non(Constante(true)), Non(Constante(false))), i1)
  evaluation(Et(Non(Constante(false)), Non(Constante(false))), i1)

  evaluation(Equivalent(Constante(true), Constante(true)), i1)
  evaluation(Equivalent(Constante(false), Constante(true)), i1)
  evaluation(Equivalent(Constante(true), Constante(false)), i1)
  evaluation(Equivalent(Constante(false), Constante(false)), i1)


}