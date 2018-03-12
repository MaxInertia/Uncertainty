package uncertainty

import utest._

object UBoolean_Tests extends TestSuite {
  override def tests: Tests = Tests {
    import uncertainty.UBoolean.uif

    'UBoolean - {
      'uif_True - {
        var x = false
        uif(True) {
          //println("Changing x to true in uif(True)")
          x = true
        }
        assert(x)
      }
      'IF_True - {
        var x = false
        IF(True) {
          //println("Changing x to true in IF(True)")
          x = true
        }
        assert(x)
      }

      'uif_False - {
        uif(False) {
          //println("entered uif(False) block")
          assert(false)
        }
      }

      'uif_False - {
        uif(False) {
          //println("entered IF(False) block")
          assert(false)
        }
      }

      /*'uif_Unclear - {
        var x = false
        uif(Unclear(50)) {
          println("Changing x to true in uif(True)")
          x = true
        }
        assert(x)
      }*/

      /*'uif_Undetermined - {
        // Currently the same as False
        uif(Undetermined) {
          assert(false)
        }
      }*/
    }
  }
}
