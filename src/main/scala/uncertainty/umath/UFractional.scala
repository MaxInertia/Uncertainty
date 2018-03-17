package uncertainty.umath

import uncertainty._

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
trait UFractional[T] extends UNumeric[T] with Fractional[T] {
  //class UFractionalOps(lhs: T) extends FractionalOps(lhs) {}
}

object UFractional {}