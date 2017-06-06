def puissance(x: Int, n: Int): Int = {
  def rec(x: Double, n: Double): Double = {
    if (n == 1) x
    else if (n % 2 != 0 && n > 2) x * rec(math.pow(x, 2), (n - 1) / 2)
    else rec(math.pow(x, 2), n / 2)
  }

  rec(x, n).toInt
}
puissance(2,2)
puissance(1,2)
puissance(3,1)
puissance(3,0)