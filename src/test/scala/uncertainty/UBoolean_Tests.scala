package uncertainty

import utest._

object UBoolean_Tests extends TestSuite {
  override def tests: Tests = Tests {
    import uncertainty.UBoolean.uif
    import uncertainty._
    'if - {

      import uncertainty.IF._

      'IF_True - {
        var x = false
        IF(True) {
          //println("Changing x to true in IF(True)")
          x = true
        }
        assert(x)
      }

      'IF_False - {
        IF(False) {
          //println("entered IF(False) block")
          assert(false)
        }
      }
    }

    'if_else - {
      'IF_True_Else - {
        var x = false
        IF(True) {
          println("Changing x to true in IF(True); ELSE")
          x = true
        } (ELSE {
          println("Ran ELSE in IF(True); ELSE")
          assert(false)
        })
        assert(x)
      }

      'IF_False_Else - {
        var x = false
        IF(False) {
          println("entered IF block in {IF(False); ELSE}")
          assert(false)
        } (ELSE {
          println("Entered ELSE block of {IF(False); ELSE}")
          x = true
        })
        assert(x)
      }
    }

    'else - {
      ELSE {
        println("The ELSE ran!")
      }
    }
  }
}
