import math._

class Complexe(val re: Double = 0, val im: Double = 0) {
  val mod: Double = math.sqrt(math.pow(re, 2) + math.pow(im, 2))
  val arg: Double = math.atan2(im, re)

  override def toString = {
    if (im < 0) re + " " + (im + "i")
    else if (re != 0 && im != 0) re + " +" + im + "i"
    else if (re == 0 && im != 0) im + "i"
    else if (re != 0) re.toString
    else ""
  }

  def +(c: Complexe) = new Complexe(re + c.re, im + c.im)

  def -(c: Complexe) = new Complexe(re - c.re, im - c.im)

  def unary_- = new Complexe(-re, -im)

  def *(c: Complexe) = new Complexe(re * c.re - im * c.im, re * c.im + im * c.re)

  def /(c: Complexe) = new Complexe(re * c.re + im * c.im, (c.re * im - re * c.im) / (math.pow(im, 2) + math.pow(c.im, 2)))

  def <(c: Complexe) = mod < c.mod

  def ==(c: Complexe) = re == c.re && im == c.im

  def max(c1: Complexe) = {
    if (this < c1) c1
    else this
  }

  def rotation(deg: Double) = {
    val rad = math.toRadians(deg)
    this * Complexe(math.cos(rad), math.sin(rad))
  }
}

object Complexe {
  def apply(re: Double, im: Double) = new Complexe(re, im)

  implicit def double2Complexe(n: Double): Complexe = new Complexe(re = n)

}

