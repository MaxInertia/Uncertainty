/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
package object uncertainty {
  
  trait ControlFlow

  object IF {
    @inline def apply(ub: UBoolean)(f: => Unit, cf: => ControlFlow = DoneIF): Unit = UBoolean.uif(ub)(f)(cf)
  }

  object ELSE extends ControlFlow {
    @inline def apply(g: => Unit): Unit = g
  }

  object DoneIF extends ControlFlow {
    @inline def apply(): Unit = Unit
  }

  // Implicit conversions between Uncertain Values
  // TODO: These appear not to be working (related to failing test: Mixing)
  implicit def int2double(ui: UInt): UDouble =
    UDouble(ui.m.toDouble, ui.u.toDouble)
  implicit def double2int(ud: UDouble): UInt =
    UInt(ud.m.toInt, ud.u.toInt)

  // Implicits for convenient creation of Uncertain Values

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
