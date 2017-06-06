import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ParenthesesSuite extends FunSuite {

  import Parentheses._

  test("equilibre du mot vide") {
    assert(equilibre("".toList))
  }

  test("equilibre ok") {
    assert(equilibre("1*(2+3)".toList))
  }

  test("equilibre imriquée ok") {
    assert(equilibre("(if (zero? x) max (/ 1 x))".toList))
    assert(equilibre("Je lui ai dit (que ce n'était pas (encore) fini).\n(Mais il n'écoutait pas...)".toList))
  }

  test("equilibre KO - nombre") {
    assert(!equilibre(":-)".toList))
  }

  test("equlibre KO - ordre") {
    assert(!equilibre("())(".toList))
    assert(!equilibre("())()(".toList))
    assert(!equilibre("(()()))(".toList))
  }

  test("equlibre generique ok") {
    assert(equilibre("".toList) == equilibreGenerique('(', ')')("".toList))
    assert(equilibre("1*(2+3)".toList) == equilibreGenerique('(', ')')("1*(2+3)".toList))
    assert(equilibre("(if (zero? x) max (/ 1 x))".toList) == equilibreGenerique('(', ')')("(if (zero? x) max (/ 1 x))".toList))
  }

  test("equlibre generique KO") {
    assert(equilibre(":-)".toList) == equilibreGenerique('(', ')')(":-)".toList))
    assert(equilibre("())()(".toList) == equilibreGenerique('(', ')')("())()(".toList))
    assert(equilibre("(()()))(".toList) == equilibreGenerique('(', ')')("(()()))(".toList))
  }

  trait xmlData {
    val xml_ok = "<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>"
    val xml_ko = "<note><to< Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>"
  }

  test("equlibreXml ok") {
    new xmlData {
      assert(equilibreXml(xml_ok.toList))
    }
  }

  test("equlibreXml ko") {
    new xmlData {
      assert(!equilibreXml(xml_ko.toList))
    }
  }
}
