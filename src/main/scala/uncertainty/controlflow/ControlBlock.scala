package uncertainty.controlflow

/**
  * Created by Dorian Thiessen on 2018-03-12.
  */
class ControlBlock(c: => List[(UBooleanFn, BlockFn)]) {
  def cases: List[(UBooleanFn, BlockFn)] = c

  //println("CB.constructor")
  def ElseIf(condition: UBoolean)(block: => Unit): ControlBlock = {
    //println("CB.ElseIf")
    new ControlBlock(cases :+ (() =>condition, () => block))
  }

  def Else(block: => Unit): ControlBlock = {
    //println("CB.Else")
    new ControlBlock(cases :+ (() =>True, () => block))
  }

  def apply(): Unit = {
    //println("CB.apply")
    execute(cases)
  }
  private def execute(left: List[(UBooleanFn, BlockFn)]): Unit = {
    //println("CB.execute")
    left.head match {
      case (conditionF, blockF) => conditionF() match {
        case False => if (1 < left.length) execute(left.tail)
        case True  => blockF()
        case _     => Unit
      }
    }
    //println("CB.execute ending")
  }
}