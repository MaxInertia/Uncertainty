package uncertainty.umath

import uncertainty._

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */

trait UIntegral[T] extends UNumeric[T] with Integral[T] {
  //class UIntegralOps(lhs: T) extends IntegralOps(lhs) {}
}

object UIntegral {}
