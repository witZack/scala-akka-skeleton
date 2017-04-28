package com.app.service.model

import org.joda.time.DateTime
import org.joda.time.format.{ DateTimeFormatter, ISODateTimeFormat }
import spray.json._
/**
 *
 */
trait ServiceJsonFormatters extends DefaultJsonProtocol {

  val formatter: DateTimeFormatter = ISODateTimeFormat.dateTime()

  // Joda - DateTime
  implicit val jodaDateTimeJFConverter = new JsonFormat[DateTime] {
    override def write(obj: DateTime): JsValue = {
      formatter.print(obj).toJson
    }
    override def read(js: JsValue): DateTime = {
      formatter.parseDateTime(js.convertTo[String])
    }
  }

}
