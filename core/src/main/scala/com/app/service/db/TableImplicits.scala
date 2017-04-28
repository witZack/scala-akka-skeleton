package com.app.service.db

import com.app.service.model.ServiceJsonFormatters
import org.joda.time.DateTime
import slick.jdbc.MySQLProfile.api._

/**
 * Object that contains all column implicit mappings for Products
 *
 */
object TableImplicits extends ServiceJsonFormatters {

  private[db] implicit val jodaDateTimeConverter = MappedColumnType.base[DateTime, String](
    { v ⇒ formatter.print(v) }, // map DateTime to String
    { v ⇒ formatter.parseDateTime(v) } // map String to DateTime
  )

  private[db] implicit val booleanConverter = MappedColumnType.base[Boolean, Int](
    { v ⇒ if (v) { 1 } else { 0 } }, // map Bool to Int
    { v ⇒ if (v == 1) { true } else { false } } // map Int to Bool
  )

}
