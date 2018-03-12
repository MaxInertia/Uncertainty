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
  protected case class Val {}
}*/

object UBoolean {
  def uif(ub: UBoolean)(f: => Unit)(cf: ControlFlow): Unit =
    ub match {
      case True         => f
      case False        => cf
      case Unclear(p)   => Unit // TODO: Both perform f, and don't perform f.
      case Undetermined => Unit
  }
}