package db.reactor.pg.dto

import db.reactor.common.dto.{AbstractSource, AbstractSourceCollection}

case class PGTable(override val name: String, rows: Long) extends AbstractSourceCollection(name = name)

case class PGDB(override val sourceName: String, override val collections: List[PGTable])
    extends AbstractSource(sourceName = sourceName, collections = collections)
