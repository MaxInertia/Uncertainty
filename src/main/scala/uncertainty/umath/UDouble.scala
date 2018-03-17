package uncertainty.umath

/**
  * Created by Dorian Thiessen on 2018-03-16.
  */
class UDouble(val m: Double, val u: Double) extends UncertainVal[Double](m ,u) {
  override def max: Double = m + u
  override def min: Double = m - u
}

object UDouble {
  def apply(x: Double, y: Double): UDouble = new UDouble(x, y)

  trait UDoubleIsFractional extends UFractional[UDouble] with Ordering[UDouble] with Equiv[UDouble] {
    override def plus(x: UDouble, y: UDouble): UDouble  = UDouble(x.m + y.m, x.u + y.u)
    override def minus(x: UDouble, y: UDouble): UDouble = UDouble(x.m - y.m, x.u + y.u)
    override def times(x: UDouble, y: UDouble): UDouble = UDouble(x.m * y.m, x.m * y.u + y.m * x.u)
    override def div(x: UDouble, y: UDouble): UDouble   = UDouble(x.m / y.m, x.u / y.m + x.m * y.u / (y.m * y.m))
    override def negate(x: UDouble): UDouble = UDouble(-x.m, x.u)

    // Exact Conversions (Uncertain -> Discrete)
    override def fromInt(x: Int): UDouble = UDouble(x.toDouble, 0)
    override def toInt(x: UDouble): Int       = x.m.toInt
    override def toLong(x: UDouble): Long     = x.m.toLong
    override def toFloat(x: UDouble): Float   = x.m.toFloat
    override def toDouble(x: UDouble): Double = x.m

    // Comparison Operations
    override def compare(x: UDouble, y: UDouble): Int =
      if (equiv(x, y)) 0
      else if (x.max < y.min) -1
      else 1
    override def equiv(x: UDouble, y: UDouble): Boolean =
      if((x.max >= y.min && x.min <= y.max)
        || (y.max >= x.min && y.min <= x.max)
        || (y.m == x.m && y.u == x.u)) true
      else false
  }

  implicit object UDoubleIsFractional extends UDoubleIsFractional
}
