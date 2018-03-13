package uncertainty

/**
  * Created by Dorian Thiessen on 2018-03-12.
  */
package object controlflow {

  //type Block = Unit
  type BlockFn = () => Unit
  type UBooleanFn = () => UBoolean

  object If {
    def apply(condition: => UBoolean)(block: => Unit): ControlBlock = {
      //println("If.apply")
      /*lazy val c: UBoolean = condition
      lazy val b: Unit = block*/
      new ControlBlock(List((() => condition, ()=>block)))
      //println("If.apply returning")
      //cb
    }
  }
}
