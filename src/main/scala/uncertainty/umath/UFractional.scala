package uncertainty.umath

import uncertainty._

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
trait UFractional[T] extends UNumeric[T] with Fractional[T] {
  // Set operations specific to Uncertain-Fractionals here

  class UFractionalOps(lhs: T) extends FractionalOps(lhs) with UOps {
    def toUInt: UInt = UFractional.this.toUInt(lhs)
    def toULong: ULong = UFractional.this.toULong(lhs)
    def toUFloat: UFloat = UFractional.this.toUFloat(lhs)
    def toUDouble: UDouble = UFractional.this.toUDouble(lhs)
  }
}
