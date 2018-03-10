package uncertainty.umath

import uncertainty._

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
trait UNumeric[T] extends Numeric[T] with Uncertain[T] {
  def toUInt(x: T): UInt
  def toULong(x: T): ULong
  def toUFloat(x: T): UFloat
  def toUDouble(x: T): UDouble
  trait UOps {
    def toUInt: UInt
    def toULong: ULong
    def toUFloat: UFloat
    def toUDouble: UDouble
  }
}

object UNumeric {

  trait UDoubleConflicted extends UNumeric[UDouble] {
    // Operations
    override def plus(x: UDouble, y: UDouble): UDouble = UDouble(x.m + y.m, x.u + y.u)
    override def minus(x: UDouble, y: UDouble): UDouble = UDouble(x.m - y.m, x.u + y.u)
    override def times(x: UDouble, y: UDouble): UDouble = UDouble(x.m * y.m, x.m * y.u + y.m * x.u)
    override def negate(x: UDouble): UDouble = UDouble(-x.m, x.u)
    // Exact Conversions (Uncertain -> Discrete)
    override def fromInt(x: Int): UDouble = UDouble(x.toDouble, 0)
    override def toInt(x: UDouble): Int       = x.m.toInt
    override def toLong(x: UDouble): Long     = x.m.toLong
    override def toFloat(x: UDouble): Float   = x.m.toFloat
    override def toDouble(x: UDouble): Double = x.m
    // Uncertain Conversions (Uncertain -> Uncertain)
    override def toUInt(x: UDouble): UInt       = UInt   (x.m.toInt,    x.u.toInt)
    override def toULong(x: UDouble): ULong     = ULong  (x.m.toLong,   x.u.toLong)
    override def toUFloat(x: UDouble): UFloat   = UFloat (x.m.toFloat,  x.u.toFloat)
    override def toUDouble(x: UDouble): UDouble = x
    // Comparator
    override def compare(x: UDouble, y: UDouble): Int =
      if ((x.m > y.min && x.min < y.max) ||
        (y.max > x.min && y.min < x.max)) 0
      else if (x.max < y.min) -1
      else -1
  }

  trait UDoubleIsFractional extends UDoubleConflicted with UFractional[UDouble] {
    // Operations
    override def div(x: UDouble, y: UDouble): UDouble = UDouble(x.m / y.m, x.u / y.m + x.m * y.u / (y.m * y.m))
  }

  trait UDoubleAsIfIntegral extends UDoubleConflicted with UIntegral[UDouble] {
    // Operations // TODO: How should Quotient and Remainder be implemented here?
    override def quot(x: UDouble, y: UDouble): UDouble = ???
    override def rem(x: UDouble, y: UDouble): UDouble = ???
  }

  implicit object UDoubleIsFractional extends UDoubleIsFractional
  implicit object UDoubleAsIfIntegral extends UDoubleAsIfIntegral

  trait UIntIsIntegral extends UIntegral[UInt] {
    // Operations
    override def plus(x: UInt, y: UInt): UInt = UInt(x.m + y.m, x.u + y.u)
    override def minus(x: UInt, y: UInt): UInt = UInt(x.m - y.m, x.u + y.u)
    override def times(x: UInt, y: UInt): UInt = UInt(x.m * y.m, x.m * y.u + y.m * x.u)
    override def negate(x: UInt): UInt = UInt(-x.m, x.u)
    // TODO: How should Quotient and Remainder be implemented here?
    override def quot(x: UInt, y: UInt): UInt = ???
    override def rem(x: UInt, y: UInt): UInt = ???
    // Exact Conversions
    override def fromInt(x: Int): UInt = UInt(x.toInt, 0)
    override def toInt(x: UInt): Int = x.m.toInt
    override def toLong(x: UInt): Long = x.m.toLong
    override def toFloat(x: UInt): Float = x.m.toFloat
    override def toDouble(x: UInt): Double = x.m.toDouble
    // Uncertain Conversions
    override def toUInt(x: UInt): UInt = x
    override def toULong(x: UInt): ULong = ULong(x.m.toLong, x.u.toLong)
    override def toUFloat(x: UInt): UFloat = UFloat(x.m.toFloat, x.u.toFloat)
    override def toUDouble(x: UInt): UDouble = UDouble(x.m.toDouble, x.u.toDouble)
    // Comparator
    override def compare(x: UInt, y: UInt): Int =
      if ((x.max > y.min && x.min < y.max) ||
        (y.max > x.min && y.min < x.max)) 0
      else if (x.max < y.min) -1
      else -1
  }

  implicit object UIntIsIntegral extends UIntIsIntegral

}
