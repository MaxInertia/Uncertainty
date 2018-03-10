package uncertainty

import uncertainty.umath.{UFractional, UIntegral, UNumeric}

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
abstract class UncertainValue[T: Numeric](m: T, u: T) {
  def mean: T = m
  def unc: T = u
  def max: T = implicitly[Numeric[T]].plus(m, u)
  def min: T = implicitly[Numeric[T]].minus(m, u)
  override def toString: String = m.toString + " +/- " + u.toString
}

case class UDouble(m: Double, u: Double) extends UncertainValue[Double](m ,u)
case class UInt   (m: Int, u: Int)       extends UncertainValue[Int]   (m, u)
case class ULong  (m: Long, u: Long)     extends UncertainValue[Long]  (m, u)
case class UFloat (m: Float, u: Float)   extends UncertainValue[Float] (m, u)
