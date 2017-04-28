package com.app.service.db

import com.app.service.model.Example
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ ExecutionContext, Future }

/**
 *
 */
trait ExampleTableQueries {
  self: ExamplesDB ⇒

  /**
   * Retrieve a single example with an id
   * @param id ID of the example
   * @return Example
   */
  def retrieveExample(id: String)(implicit ec: ExecutionContext): Future[Example] =
    db.run(table.filter(p ⇒ p.id === id).result.head).flatMap({
      case e: Example ⇒ Future.successful(e)
      case _          ⇒ Future.failed(new NullPointerException(s"Unable to retrieve example with id: $id"))
    })
}
