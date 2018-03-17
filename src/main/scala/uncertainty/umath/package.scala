package uncertainty

/**
  * Useful implicits for working with UNumerics
  */
package object umath {
  implicit class UIntegralOps[T](lhs: T)(implicit ev: Integral[T]) {
    def +(rhs: T): T = ev.plus(lhs, rhs)
    def -(rhs: T): T = ev.minus(lhs, rhs)
    def *(rhs: T): T = ev.times(lhs, rhs)
  }

  implicit class UFractionalOps[T](lhs: T)(implicit ev: Fractional[T]) {
    def +(rhs: T): T = ev.plus(lhs, rhs)
    def -(rhs: T): T = ev.minus(lhs, rhs)
    def *(rhs: T): T = ev.times(lhs, rhs)
    def /(rhs: T): T = ev.div(lhs, rhs)
  }

  implicit class UOrderingOps[T](lhs: T)(implicit ev: Ordering[T]) {
    def <(rhs: T): Boolean = ev.compare(lhs, rhs) == -1
    def <=(rhs: T): Boolean = ev.compare(lhs, rhs) <= 0
    def >(rhs: T): Boolean = ev.compare(lhs, rhs) == 1
    def >=(rhs: T): Boolean = ev.compare(lhs, rhs) >= 0
  }

  implicit class UEquivOps[T](lhs: T)(implicit ev: Equiv[T]) {
    def ===(rhs: T): Boolean = ev.equiv(lhs, rhs)
  }

  // Syntactically pleasing constructors
  // 'x +- y' and 'x +/- y' where x and y numeric (Currently only Double and Int)

  class DoubleI(self: Double) {
    implicit def +-(u: Double): UDouble =
      UDouble(self, u)
    implicit def +/-(u: Double): UDouble =
      UDouble(self, u)
  }
  implicit def doubleExt(self: Double): DoubleI = new DoubleI(self)

  class IntI(self: Int) {
    implicit def +-(u: Int): UInt =
      UInt(self, u)
    implicit def +/-(u: Int): UInt =
      UInt(self, u)
  }
  implicit def intExt(self: Int): IntI = new IntI(self)
}
