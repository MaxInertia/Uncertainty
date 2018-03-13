package uncertainty.controlflow

import utest._

object UBoolean_Tests extends TestSuite {
  override def tests: Tests = Tests {

    // If

    'IF_True - {
      var x = false
      If(True) {
        println("Changing x to true in IF(True)")
        x = true
      }()
      assert(x)
    }

    'IF_False - {
      If(False) {
        println("entered IF(False) block")
        assert(false)
      }()
    }

    // If Else

    'IF_True_Else - {
      var x = false
      If(True) {
        println("entered IF block in {IF(True); ELSE}")
        x = true
      }.Else{
        println("entered ELSE block in {IF(True); ELSE}")
        assert(false)
      }()
      assert(x)
    }

    'IF_False_Else - {
      var x = false
      If(False) {
        println("entered IF block in {IF(False); ELSE}")
        assert(false)
      }.Else{
        println("Entered ELSE block of {IF(False); ELSE}")
        x = true
      }()
      assert(x)
    }

    // If ElseIf

    'If_True_ElseIf_False - {
      var x = false
      If(True) {
        println("entered If block in {If(True); ElseIf(False)")
        x = true
      }.ElseIf(False) {
        println("entered ElseIf block in {If(True); ElseIf(False)}")
        assert(false)
      }()
      assert(x)
    }

    'If_False_ElseIf_True - {
      var x = false
      If(False) {
        println("entered If block in {If(True); ElseIf(False)")
        assert(false)
      }.ElseIf(True) {
        println("entered ElseIf block in {If(True); ElseIf(False)}")
        x = true
      }()
      assert(x)
    }

    // If ElseIf Else

    'If_True_ElseIf_False_Else - {
      var x = false
      If(True)({
        println("entered If block in {If(True); ElseIf(False); Else}")
        x = true
      }).ElseIf(False) {
        println("entered ElseIf block in {If(True); ElseIf(False); Else}")
        assert(false)
      } .Else {
        println("entered Else block in {If(True); ElseIf(False); Else}")
        assert(false)
      }()
      assert(x)
    }

    'If_False_ElseIf_True_Else - {
      var x = false
      If(False) {
        println("entered If block in {If(False); ElseIf(True); Else}")
        assert(false)
      }. ElseIf (True) {
        println("entered ElseIf block in {If(False); ElseIf(True); Else}")
        x = true
      } .Else {
        println("entered Else block in {If(False); ElseIf(True); Else}")
        assert(false)
      }()
      assert(x)
    }

    'If_False_ElseIf_False_Else - {
      var x = false
      If(False) {
        println("entered If block in {If(False); ElseIf(False); Else}")
        assert(false)
      }. ElseIf(False) {
        println("entered ElseIf block in {If(False); ElseIf(False); Else}")
        assert(false)
      }. Else {
        println("entered Else block in {If(False); ElseIf(False); Else}")
        x = true
      }()
      assert(x)
    }

  }
}
