package uncertainty.umath

/**
  * Created by Dorian Thiessen on 2018-03-16.
  */
abstract class UncertainVal[T: Numeric](m: T, u: T) {
  def mean: T = m
  def unc: T = u
  def max: T = implicitly[Numeric[T]].plus(m, u)
  def min: T = implicitly[Numeric[T]].minus(m, u)
  override def toString: String = m.toString + " +/- " + u.toString
}
