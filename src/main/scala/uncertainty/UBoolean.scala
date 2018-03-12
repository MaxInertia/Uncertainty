package uncertainty

trait UBoolean extends Uncertain[Boolean]

/**
  * True for whole range of possible values
  */
case object True extends UBoolean

/**
  * False for whole range of possible values
  */
case object False extends UBoolean

/**
  * Truth value is unknown
  */
case object Undetermined extends UBoolean

/**
  * Truth value has uncertainty
  * @param p Probability of truth
  *          range: (0, 1) (exclusive)
  */
case class Unclear(p: Double) extends UBoolean

/*// Could also be defined as an enumeration
object UBoolean extends Enumeration {
  protected case class Val {

  }
}*/

object UBoolean {
  private type UBoolCheck = UBoolean => (Unit => Unit)
  def uif(ub: UBoolean)(f: Unit): Unit = ub match {
    case True         =>
      println("Matched True")
      f
    case False        =>
      println("Matched False")
      Unit
    case Unclear(p)   =>
      println(s"Matched Unclear($p)")
      Unit
    case Undetermined =>
      println("Matched Undetermined")
      Unit
  }
}