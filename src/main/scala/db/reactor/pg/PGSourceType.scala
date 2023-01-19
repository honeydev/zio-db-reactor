package db.reactor.pg

import db.reactor.common.SourceType

trait PGSourceType extends SourceType {

  override def asString: String = "pg"

  def getType = PGSourceType
}

object PGSourceType
