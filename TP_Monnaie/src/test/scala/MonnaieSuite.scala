import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MonnaieSuite extends FunSuite {

  import Monnaie._

  trait MonnaieDispo {
    val billetDispo_ok = List(10, 20, 50)
    val centsDispo_ok = List(1, 2, 5, 10, 20, 50)
  }

  test("Compte simple") {
    assert(comptePossibilite(4, List(1, 2)) == 3)
    assert(comptePossibilite(4, List(2)) == 1)
    assert(comptePossibilite(4, List(1)) == 1)
  }

  test("Compte grand") {
    new MonnaieDispo {
      assert(comptePossibilite(42, centsDispo_ok) == 271)
      assert(comptePossibilite(89, centsDispo_ok) == 2974)
      assert(comptePossibilite(70, billetDispo_ok) == 6)
      assert(comptePossibilite(42, billetDispo_ok) == 0)
    }
  }

  test("Distribution optimale") {
    new MonnaieDispo {
      assert(distributionOptimale(10, billetDispo_ok) == (1, 0, 0))
      assert(distributionOptimale(0, billetDispo_ok) == (0, 0, 0))
      assert(distributionOptimale(20, billetDispo_ok) == (2, 0, 0))
      assert(distributionOptimale(120, billetDispo_ok) == (3, 2, 1))
      assert(distributionOptimale(580, billetDispo_ok) == (9, 7, 7))
      assert(distributionOptimale(42, billetDispo_ok) == (0, 0, 0))
    }
  }


}

