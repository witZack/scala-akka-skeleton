package com.app.service.db

import slick.jdbc.MySQLProfile.api._

/**
 *
 */
trait ExamplesDB {

  val db: Database = Database.forConfig("exampleDB")
  val table = TableQuery[Examples]

}
