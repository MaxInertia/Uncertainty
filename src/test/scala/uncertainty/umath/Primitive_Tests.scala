package uncertainty.umath

import utest._

/**
  * Created by Dorian Thiessen on 2018-03-09.
  */
object Primitive_Tests extends TestSuite {
  override def tests: Tests = Tests {
    val EPSILON = 0.00001

    'UDouble - {
      'create - {
        val ud = UDouble(2155.0, 43.2)
        ud + ud
      }

      'create_wImplicits - {
        val ud0 = UDouble(21.2, 1.7)
        val ud1 = 21.2 +- 1.7
        val ud2 = 21.2 +/- 1.7
      }

      'comparisons - {
        '=== {
          val ud0 = UDouble(21.2, 1.7)
          val ud1 = 21.2 +- 1.7
          val ud2 = 21.2 +/- 1.7
          assert(ud0 === ud0)
          assert(ud1 === ud1)
          assert(ud2 === ud2)
          assert(ud0 === ud1)
          assert(ud1 === ud2)
        }

        val uSmall = 4.0 +- 1.0
        val uMedium = 8.0 +- 1.0
        val uLarge = 24.0 +- 1.0

        '< {
          assert(uSmall < uMedium)
          assert(uMedium < uLarge)
          assert(uSmall < uLarge)
          assert(uSmall <= uMedium)
          assert(uMedium <= uLarge)
          assert(uSmall <= uLarge)
        }

        '> {
          assert(uMedium > uSmall)
          assert(uLarge > uMedium)
          assert(uLarge > uSmall)
          assert(uMedium >= uSmall)
          assert(uLarge >= uMedium)
          assert(uLarge >= uSmall)
        }
      }

      'ops - {
        '+ {
          val ud1 = 3.2 +- 0.1
          val ud2 = 6.8 +/- 0.4
          val result = ud1 + ud2
          val diff = result.m - (ud1.m + ud2.m)
          assert(diff < EPSILON)
        }
        '- {
          val ud1 = 3.2 +- 0.1
          val ud2 = 6.8 +/- 0.4
          val result = ud1 - ud2
          val diff = result.m - (ud1.m - ud2.m)
          assert(diff < EPSILON)
        }
        '* {
          val ud1 = 3.2 +- 0.1
          val ud2 = 6.8 +/- 0.4
          val result = ud1 * ud2
          val diff = result.m - (ud1.m * ud2.m)
          assert(diff < EPSILON)
        }
        '/ {
          val ud1 = 3.2 +- 0.1
          val ud2 = 6.8 +/- 0.4
          val result = ud1 / ud2
          val diff = result.m - (ud1.m / ud2.m)
          assert(diff < EPSILON)
        }
      }

      'conversions - {
        import UDouble.UDoubleIsFractional._
        'fromInt - {
          val i: Int = 9
          assert(fromInt(i) === UDouble(9.0, 0.0))
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
      import UInt.UIntIsIntegral._

      'create - {
        val ud = UInt(2155, 43)
      }
      'create_wImplicits - {
        val ui1 = 21 +- 1
        val ui2 = 21 +/- 1
        assert(ui1 === ui2)
      }

      'comparisons - {
        '=== {
          val ud0 = UDouble(21.2, 1.7)
          val ud1 = 21.2 +- 1.7
          val ud2 = 21.2 +/- 1.7

          assert(ud0 === ud0)
          assert(ud1 === ud1)
          assert(ud2 === ud2)

          assert(ud0 === ud1)
          assert(ud1 === ud2)
        }

        val uSmall = 4.0 +- 1.0
        val uMedium = 8.0 +- 1.0
        val uLarge = 24.0 +- 1.0

        '< {
          assert(uSmall < uMedium)
          assert(uMedium < uLarge)
          assert(uSmall < uLarge)
          assert(uSmall <= uMedium)
          assert(uMedium <= uLarge)
          assert(uSmall <= uLarge)
        }

        '> {
          assert(uMedium > uSmall)
          assert(uLarge > uMedium)
          assert(uLarge > uSmall)
          assert(uMedium >= uSmall)
          assert(uLarge >= uMedium)
          assert(uLarge >= uSmall)
        }

        val uA = 5.0 +- 2.5
        val uB = 7.5 +- 2.5
        val uC = 12.5 +- 2.5

        '<= {
          assert(uA <= uA)
          assert(uB <= uB)
          assert(uC <= uC)
          assert(uA <= uB)
          assert(uB <= uC)
        }

        '>= {
          assert(uA >= uA)
          assert(uB >= uB)
          assert(uC >= uC)
          assert(uA >= uB)
          assert(uB >= uC)
        }
      }

      'ops - {
        '+ {
          val ui1 = 3 +- 33
          val ui2 = 6 +/- 2
          val result = ui1 + ui2
          assert(result === UInt(ui1.m + ui2.m, ui1.u + ui2.u))
        }
        '- {
          val ui1 = 15 +- 2
          val ui2 = 22 +/- 5
          val result = ui1 - ui2
          assert(result === UInt(ui1.m - ui2.m, ui1.u + ui2.u))
        }
        '* {
          val ui1 = 16 +- 3
          val ui2 = 6 +/- 1
          val result = ui1 * ui2
          assert(result === UInt(ui1.m * ui2.m, ui1.m * ui2.u + ui1.u * ui2.m))
        }
      }

      'conversions - {
        'fromInt - {
          val i = 9
          assert(fromInt(i) === UInt(i, 0))
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

    /*'Mixing - { // TODO: Implement
      'UInt_UDouble - {
        val ui: UInt = 5 +- 2
        val ud: UDouble = 5.0 +- 2.0
        //println(s"ud + ui = (${ud + ui})")
        assert(false)
      }
    }*/

  }
}
