package com.app.service.db

import com.app.service.model.Example
import org.joda.time.DateTime
import slick.jdbc.MySQLProfile.api._
/**
 * Example table definition
 */
class Examples(tag: Tag) extends Table[Example](tag, "examples") {
  import TableImplicits._

  def id = column[String]("id", O.PrimaryKey)
  def description = column[String]("description")
  def enabled = column[Boolean]("enabled")
  def created_at = column[DateTime]("created_at")
  def updated_at = column[DateTime]("updated_at")

  def * = (id, description, enabled, created_at, updated_at) <> (Example.tupled, Example.unapply)
}
