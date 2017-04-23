import Cadeau._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CadeauSuite extends FunSuite{
  test("répartition équilibrée"){
    assert( repartition(10, List(4, 4, 4) ) == Some(List(3, 3, 4)))
    assert( repartition(10, List(5, 10, 3) ) == Some(List(3, 3, 4)))
    assert( repartition(100, List(40, 40, 40)) == Some(List(33, 33, 34)))
  }

  test("répartition déséquilibrée"){
    assert( repartition(100, List(100, 1, 60)) == Some(List(1, 49, 50)) )
    assert( repartition(100, List(100, 100, 10)) == Some(List(10, 45, 45)) )
    assert( repartition(100, List(100, 15, 35)) == Some(List(15, 35, 50)) )
    assert( repartition(100, List(50, 15, 35)) == Some(List(15, 35, 50)) )
  }

  test("répartition impossible"){
    assert( repartition(100,List(50, 20, 20)) == None )
    assert( repartition(100,List(40, 10, 20)) == None )
    assert( repartition(100,List(10, 50, 35)) == None )
  }



}
