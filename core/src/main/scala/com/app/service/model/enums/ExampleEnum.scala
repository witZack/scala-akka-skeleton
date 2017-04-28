package com.app.service.model.enums

import enumeratum.{ Enum, EnumEntry }
import enumeratum.EnumEntry.UpperSnakecase

/**
 * We use upper snake case so it emphasises that it's an enum, snake cases it if need be
 * and generates the values off the class name
 */
sealed trait ExampleEnum extends EnumEntry with UpperSnakecase

object ExampleEnums extends Enum[ExampleEnum] with UpperSnakecase {

  val values = findValues

  case object Example$ extends ExampleEnum

}
