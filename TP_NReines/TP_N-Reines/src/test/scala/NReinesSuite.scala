import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NReinesSuite extends  FunSuite{

  trait reinesData{
    val r2 = new NReines(2)
    val r4 = new NReines(4)
    val r8 = new NReines(8)
  }

  test("estOk"){
    new reinesData {
      assert( r4.estOk(3, List(0,2)) )
      assert( !r4.estOk(2, List(0,2)) )
    }
  }

  test("solutions"){
    new reinesData {
      assert( r2.solutions.toSet == Set())
      assert( r4.solutions.toSet == Set(List(2, 0, 3, 1), List(1, 3, 0, 2) ) )
      assert( Set(List(3, 1, 6, 2, 5, 7, 4, 0), List(4, 0, 7, 5, 2, 6, 1, 3), List(2, 7, 3, 6, 0, 5, 1, 4)).subsetOf( r8.solutions.toSet ) )
    }
  }

  test("Nombre de solution(s)"){
    new reinesData {
      assert(r2.nombreSolutions == 0)
      assert(r4.nombreSolutions == 2)
      assert(r8.nombreSolutions == 92)
    }
  }


}
