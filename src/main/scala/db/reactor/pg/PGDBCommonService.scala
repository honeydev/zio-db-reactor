package db.reactor.pg

import db.reactor.common.CommonDataService
import db.reactor.common.dto.PGTableMeta
import db.reactor.config.PGSource
import zio._
import zio.jdbc._

class PGDBCommonService(
  val source: PGSource,
  val layer: ZLayer[ZConnectionPoolConfig, Throwable, ZConnectionPool],
  val config: ULayer[ZConnectionPoolConfig]
) extends CommonDataService {

  def selectSourceData: ZIO[Any, Throwable, PGTableMeta] = {
    val res: ZIO[ZConnectionPool, Throwable, PGTableMeta] = for {
      tablesRowsCounts <- transaction {
        val resultE = source.tables.map(x => {
          selectOne(sql"SELECT COUNT(*)".from(x.name).as[Long])
        })
        ZIO.collectAll(resultE)
      }
    } yield PGTableMeta(name = "testName", rowsCount = tablesRowsCounts.map {
      case Some(v) => v
      case _ => 0
    }.sum)
    res.provideLayer(layer).provideLayer(config)
  }
}
