import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class EnsembleFonctionSuite extends FunSuite {

  // on importe l'ensemble de ce qui est défini dans l'objet EnsemblesFonctions
  import EnsemblesFonctions._

  trait EnsemblesTests {
    val s1 = singleton(1)
    val s2 = singleton(2)
    val s3 = singleton(3)
  }

  test("contient fonctionne") {
    assert(contient(x => true, 100))
  }

  test("singleton(1) contient 1") {
    new EnsemblesTests {
      assert(contient(s1, 1), "Singleton 1")
      assert(!contient(s2, 1), "Singleton 2")
      assert(!contient(s3, 1), "Singleton 3")
    }
  }

  test("union contient ces éléments") {
    new EnsemblesTests {
      val s = union(s1, s2)
      assert(contient(s, 1), "Union 1")
      assert(contient(s, 2), "Union 2")
      assert(!contient(s, 3), "Union 3")
    }
  }

  test("intersection contient ces éléments") {
    new EnsemblesTests {
      val t = union(s1, s2)
      val u = union(s3, s2)
      val s = intersection(t, u)

      assert(!contient(s, 1), "Intersection 1")
      assert(contient(s, 2), "Intersection 2")
      assert(!contient(s, 3), "Intersection 3")
    }
  }

  test("difference contient ces éléments") {
    new EnsemblesTests {
      val t = union(s1, s2)
      val u = union(s3, s2)
      val s = difference(t, u)

      assert(contient(s, 1), "Différence 1")
      assert(!contient(s, 2), "Différence 2")
      assert(!contient(s, 3), "Différence 3")
    }
  }

  test("filterr") {
    new EnsemblesTests {
      val t = union(s1, s2)
      val u = union(t, s3)
      val s = filtrer(u, x => x <= 2)

      assert(contient(s, 1), "Filtrer 1")
      assert(contient(s, 2), "Filtrer 2")
      assert(!contient(s, 3), "filtrer 3")
    }
  }

  test("pourTout") {
    new EnsemblesTests {
      val t = union(s1, s2)
      val u = union(t, s3)
      assert(pourTout(u, x => x > 0), "pourTout 1")
      assert(!pourTout(u, x => x < 0), "pourTout 1")
    }
  }

  test("ilExiste") {
    new EnsemblesTests {
      val t = union(s1, s2)
      val u = union(t, s3)

      assert(ilExiste(u, x => x < 2), "ilExiste 1")
      assert(ilExiste(u, x => x > 2), "ilExiste 2")
      assert(ilExiste(u, x => x > 0), "ilExiste 3")
      assert(!ilExiste(u, x => x < 0), "ilExiste 4")

    }
  }

  test("image") {
    new EnsemblesTests {
      val t = union(s1, s2)
      val u = union(t, s3)
      val s = image(u, x => x * x)


      assert(contient(s, 1), "image 1")
      assert(contient(s, 4), "image 2")
      assert(contient(s, 9), "image 3")
      assert(!contient(s, 16), "image 4")

    }
  }
}
