package uncertainty.umath

import uncertainty._

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
trait UIntegral[T] extends UNumeric[T] with Integral[T] {
  // Set operations specific to Uncertain-Integrals here

  class UIntegralOps(lhs: T) extends IntegralOps(lhs) with UOps {
    def toUInt: UInt = UIntegral.this.toUInt(lhs)
    def toULong: ULong = UIntegral.this.toULong(lhs)
    def toUFloat: UFloat = UIntegral.this.toUFloat(lhs)
    def toUDouble: UDouble = UIntegral.this.toUDouble(lhs)
  }
}
