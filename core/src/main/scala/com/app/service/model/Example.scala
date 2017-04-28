package com.app.service.model

import org.joda.time.DateTime

/**
 * Example case class for table
 */
case class Example(
  id:          String,
  description: String,
  enabled:     Boolean,
  createdAt:   DateTime,
  updatedAt:   DateTime)
