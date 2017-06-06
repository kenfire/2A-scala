import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LogiqueSuite extends FunSuite {

  import Logique._

  trait exprData {
    val e1 = Equivalent(Proposition("R"), Et(Proposition("P"), Et(Proposition("A"), Proposition("S"))))
    val e2 = Proposition("P")
    val e3 = Non(Proposition("R"))
    val th = Ou(Non(Proposition("A")), Non(Proposition("S")))

    val i1 = Map("A" -> true, "P" -> true, "R" -> false, "S" -> false)
    val i2 = Map("A" -> false, "P" -> false, "R" -> true, "S" -> true)
    val i3 = Map("A" -> true, "P" -> false, "R" -> false, "S" -> true)
  }

  test("Evaluation") {
    new exprData {
      assert(evaluation(e1, i1))
      assert(!evaluation(e1, i2))
      assert(evaluation(e1, i3))

      assert(evaluation(e2, i1))
      assert(!evaluation(e2, i2))
      assert(!evaluation(e2, i3))

      assert(evaluation(e3, i1))
      assert(!evaluation(e3, i2))
      assert(evaluation(e3, i3))

      assert(evaluation(th, i1))
      assert(evaluation(th, i2))
      assert(!evaluation(th, i3))
    }
  }

  test("Ensemble propositions") {
    new exprData {
      assert(ensembleProposition(List(e1)) == Set("A", "P", "R", "S"))
      assert(ensembleProposition(List(e2, e3)) == Set("P", "R"))
      assert(ensembleProposition(List(e2, e3, th)) == Set("A", "P", "R", "S"))
    }
  }

  test("Liste interprétation") {
    new exprData {
      assert(listeInterpretation(Set("A")) == List(Map("A" -> true), Map("A" -> false)))
      assert(listeInterpretation(Set("A", "B")) == List(Map("A" -> true, "B" -> true), Map("A" -> true, "B" -> false), Map("A" -> false, "B" -> false), Map("A" -> false, "B" -> true)))
      assert(listeInterpretation(Set("B", "A")) == List(Map("A" -> true, "B" -> true), Map("A" -> true, "B" -> false), Map("A" -> false, "B" -> false), Map("A" -> false, "B" -> true)))
    }
  }

  test("Tautologie") {
    assert(tautologie(Constante(true)))
    assert(!tautologie(Constante(false)))

    assert(tautologie(Ou(Non(Proposition("A")), Proposition("A"))))
    assert(tautologie(Implique(Proposition("A"), Proposition("A"))))
    assert(tautologie(Equivalent(Proposition("A"), Proposition("A"))))

    assert(!tautologie(Et(Non(Proposition("A")), Proposition("A"))))
    new exprData {
      assert(!tautologie(e1))
      assert(!tautologie(e2))
      assert(!tautologie(e3))
      assert(!tautologie(th))
    }

  }

  test("Consistante") {
    assert(consistante(Constante(true)))
    assert(!consistante(Constante(false)))

    assert(consistante(Ou(Non(Proposition("A")), Proposition("A"))))
    assert(consistante(Implique(Proposition("A"), Proposition("A"))))
    assert(consistante(Equivalent(Proposition("A"), Proposition("A"))))

    assert(!consistante(Et(Non(Proposition("A")), Proposition("A"))))

    new exprData {
      assert(consistante(e1))
      assert(consistante(e2))
      assert(consistante(e3))
      assert(consistante(th))
    }

  }

  test("Affichage") {
    new exprData {
      assert(affichage(e1) == "R <=> P et A et S")
      assert(affichage(e2) == "P")
      assert(affichage(e3) == "!R")
      assert(affichage(th) == "!A ou !S")

      val e = Implique(Equivalent(Ou(Proposition("A"), Constante(false)), Proposition("B")), Non(Et(Constante(true), Proposition("A"))))
      assert(affichage(e) == "(A ou Faux <=> B) => !(Vrai et A)")
    }
  }

  test("Tableau - preuve") {
    new exprData {
      assert((new Tableau(List(e1, e2, e3), th)).preuve())
      assert(!(new Tableau(List(e1, e2), th)).preuve())
      assert(!(new Tableau(List(e1, e3), th)).preuve())
    }
  }

  test("Tableau - affichage") {
    new exprData {
      assert((new Tableau(List(e1, e2, e3), th)).toStringSelectif(l => true) == "+---------+-------+----+\n| A P R S | 1 2 3 | Th |\n+---------+-------+----+\n| V V V V | V V F | F  |\n| V V V F | F V F | V  |\n| V V F F | V V V | V  |\n| V V F V | F V V | F  |\n| V F F V | V F V | F  |\n| V F F F | V F V | V  |\n| V F V F | F F F | V  |\n| V F V V | F F F | F  |\n| F F V V | F F F | V  |\n| F F V F | F F F | V  |\n| F F F F | V F V | V  |\n| F F F V | V F V | V  |\n| F V F V | V V V | V  |\n| F V F F | V V V | V  |\n| F V V F | F V F | V  |\n| F V V V | F V F | V  |\n+---------+-------+----+")
    }
  }


}



