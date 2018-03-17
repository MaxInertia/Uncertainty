package uncertainty.umath

/**
  * Created by Dorian Thiessen on 2018-03-16.
  */
class UInt(val m: Int, val u: Int) extends UncertainVal[Int](m, u)

object UInt {
  def apply(m: Int, u: Int): UInt = new UInt(m, u)

  trait UIntIsIntegral extends Integral[UInt] {
    override def plus(x: UInt, y: UInt): UInt = UInt(x.m + y.m, x.u + y.u)
    override def minus(x: UInt, y: UInt): UInt = UInt(x.m - y.m, x.u + y.u)
    override def times(x: UInt, y: UInt): UInt = UInt(x.m * y.m, x.m * y.u + y.m * x.u)
    override def negate(x: UInt): UInt = UInt(-x.m, x.u)
    // TODO: How should Quotient and Remainder be implemented here?
    override def quot(x: UInt, y: UInt): UInt = ???
    override def rem(x: UInt, y: UInt): UInt = ???
    // Exact Conversions
    override def fromInt(x: Int): UInt = UInt(x, 0)
    override def toInt(x: UInt): Int = x.m.toInt
    override def toLong(x: UInt): Long = x.m.toLong
    override def toFloat(x: UInt): Float = x.m.toFloat
    override def toDouble(x: UInt): Double = x.m.toDouble
    // Comparator
    override def compare(x: UInt, y: UInt): Int =
      if ((x.max >= y.min && x.min <= y.max)
        || (y.max >= x.min && y.min <= x.max)
        || (y.m == x.m && y.u == x.u)) 0
      else if (x.max < y.min) -1
      else 1

    override def equiv(x: UInt, y: UInt): Boolean =
      if((x.max >= y.min && x.min <= y.max)
        || (y.max >= x.min && y.min <= x.max)
        || (y.m == x.m && y.u == x.u)) true
      else false
  }

  implicit object UIntIsIntegral extends UIntIsIntegral
}