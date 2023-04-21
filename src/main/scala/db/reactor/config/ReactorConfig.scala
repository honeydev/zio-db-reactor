package db.reactor.config

import db.reactor.common.SourceServicesFactory
import db.reactor.pg.{PGServicesFactory, PGSourceType}

sealed abstract class Source() {

  def sourceServiceF(): SourceServicesFactory

}

object Source {
  type Sources = List[Source]
}

case class Field(name: String, `type`: String)

case class Table(name: String, fields: List[Field])

case class PGSource(
  host: String,
  port: Int,
  name: String,
  database: String,
  username: String,
  password: String,
  tables: List[Table]
) extends Source
    with PGSourceType {

  def sourceServiceF(): SourceServicesFactory =
    new PGServicesFactory(this)
}
