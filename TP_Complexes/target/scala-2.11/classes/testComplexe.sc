object testComplexe {
  println("// 1)")
  val c1 = new Complexe(1, 2)
  val c2 = new Complexe(1, -2)
  val c3 = new Complexe(im = 2)
  val c4 = new Complexe(1)
  val c5 = new Complexe()

  println("// 2)")
  c1.toString
  c2.toString
  c3.toString
  c4.toString
  c5.toString

  println("// 3)")
  //  val companion =  Complexe(3, 4)
  val companion = Complexe(math.sqrt(2), math.sqrt(2))

  println("// 4)")
  companion.mod
  companion.arg

  println("// 5)")
  c1 + c2
  c1 - c2
  c1 * c2
  c1 / c2

  println("// 6)")
  c1 + 2.0
  2.0 + c1
  2.0 - c1
  2.0 * c1
  c1 / 2.0

  println("// 7)")
  c1.mod
  c3.mod
  c1 < c3

  println("// 8)")
  c1 == c1
  c1 == c2

  println("// 9)")
  c1.max(c2)

  println("// 10)")
  c1.rotation(360)
  c1.rotation(180)
}
