package uncertainty.umath

import uncertainty._
import utest._

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
object Primitive_Tests extends TestSuite {
  override def tests: Tests = Tests {

    'UDouble - {
      'create - {
        import UNumeric.UDoubleIsFractional._
        val ud = UDouble(2155.0, 43.2)
        ud+ud
      }
      'create_wImplicits - {
        val ud0 = UDouble(21.2, 1.7)
        val ud1 = 21.2 +- 1.7
        val ud2 = 21.2 +/- 1.7
        assert(ud0 == ud1)
        assert(ud1 == ud2)
      }

      'ops - {
        import UNumeric.UDoubleIsFractional._ // <- Required for operations on UDouble
        'add - {
          val ud1 = 3.2 +- 0.1
          val ud2 = 6.8 +/- 0.4
          val result = ud1 + ud2
          assert(result == UDouble(ud1.m + ud2.m, ud1.u + ud2.u))
        }
        'sub - {
          val ud1 = 3.2 +- 0.1
          val ud2 = 6.8 +/- 0.4
          val result = ud1 - ud2
          assert(result == UDouble(ud1.m - ud2.m, ud1.u + ud2.u))
        }
        'mult - {
          val ud1 = 3.2 +- 0.1
          val ud2 = 6.8 +/- 0.4
          val result = ud1 * ud2
          assert(result == UDouble(ud1.m * ud2.m, ud1.m * ud2.u + ud1.u * ud2.m))
        }
        'div - {
          val ud1 = 6.8 +/- 0.4
          val ud2 = 3.2 +- 0.1
          val result = ud1 / ud2
          assert(result.m == ud1.m / ud2.m)
        }
      }

      'conversions - {
        import UNumeric.UDoubleIsFractional._
        'fromInt - {
          val i = 9
          assert(fromInt(i) == UDouble(i.toDouble, 0))
        }
        'toInt - {
          val ud = 3.2 +- 0.1
          val int: Int = 3
          assert(int == ud.toInt)
        }
        'toLong - {
          val ud = 3.2 +- 0.1
          val long: Long = 3
          assert(long == ud.toLong)
        }
        'toFloat - {
          val ud = 3.2 +- 0.1
          val float: Float = 3.2.toFloat
          assert(float == ud.toFloat)
        }
        'toDouble - {
          val ud = 3.2 +- 0.1
          val double: Double = 3.2
          assert(double == ud.m)
        }
      }
    }

    'UInt - {
      'create - {
        val ud = UInt(2155, 43)
      }
      'create_wImplicits - {
        val ui1 = 21 +- 1
        val ui2 = 21 +/- 1
        assert(ui1 == ui2)
      }

      'ops - {
        import UNumeric.UIntIsIntegral._
        'add - {
          val ui1 = 3 +- 33
          val ui2 = 6 +/- 2
          val result = ui1 + ui2
          assert(result == UInt(ui1.m + ui2.m, ui1.u + ui2.u))
        }
        'sub - {
          val ui1 = 15 +- 2
          val ui2 = 22 +/- 5
          val result = ui1 - ui2
          assert(result == UInt(ui1.m - ui2.m, ui1.u + ui2.u))
        }
        'mult - {
          val ui1 = 16 +- 3
          val ui2 = 6 +/- 1
          val result = ui1 * ui2
          assert(result == UInt(ui1.m * ui2.m, ui1.m * ui2.u + ui1.u * ui2.m))
        }
      }

      'conversions - {
        import UNumeric.UIntIsIntegral._
        'fromInt - {
          val i = 9
          assert(fromInt(i) == UInt(i, 0))
        }
        'toInt - {
          val ui: UInt = 7 +- 1
          val int: Int = 7
          assert(int == ui.toInt)
        }
        'toLong - {
          val ui = 7 +- 2
          val long: Long = 7
          assert(long == ui.toLong)
        }
        'toFloat - {
          val ud = 7 +- 3
          val float: Float = 7.toFloat
          assert(float == ud.toFloat)
        }
        'toDouble - {
          val ud = 7 +- 4
          val double: Double = 7.0
          assert(double == ud.toDouble())
        }
      }
    }

    /*'Mixing - {
      'UInt_UDouble - { // TODO: Figure out why this fails
        import UNumeric.UIntIsIntegral._
        import UNumeric.UDoubleIsFractional._
        import uncertainty._
        val ui = 5 +- 2
        val ud = 5.0 +- 2.0
        //ui + ud
        //ud + ui
      }
    }*/

  }
}
