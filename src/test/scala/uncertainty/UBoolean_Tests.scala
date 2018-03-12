package uncertainty

import utest._

object UBoolean_Tests extends TestSuite {
  override def tests: Tests = Tests {
    import uncertainty.UBoolean.uif
    'UBoolean - {
      'uif_True - {
        var x = false
        uif(True) {
          println("Changing x to true in uif(True)")
          x = true
        }
        assert(x)
      }
      'uif_False - {
        var y = false
        uif(False) {
          println("Changing y to true in uif(False)")
          y = true
        }
        assert(!y)
      }
    }
  }
}