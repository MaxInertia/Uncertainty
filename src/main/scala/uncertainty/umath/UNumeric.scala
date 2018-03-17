package uncertainty.umath

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
trait UNumeric[T] extends Numeric[T] {

  // TODO: Try using Phantom types to differentiate Integrals and Fractionals?

  /*class UOps(lhs: T) extends Ops(lhs) {
    def toUInt: UInt
    def toULong: ULong
    def toUFloat: UFloat
    def toUDouble: UDouble
  }*/
}

object UNumeric {}
