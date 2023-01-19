package db.reactor.pg

import db.reactor.common.{CommonDataService, SourceServicesFactory}
import db.reactor.config.{PGSource, Source}
import org.postgresql.ds.PGSimpleDataSource
import zio._
import zio.jdbc._

import java.io.Closeable

class DS extends PGSimpleDataSource with Closeable {
  override def close(): Unit = {}

}
class PGServicesFactory(val source: PGSource) extends SourceServicesFactory {

  val createZIOPoolConfig: ULayer[ZConnectionPoolConfig] =
    ZLayer.succeed(ZConnectionPoolConfig.default)

  val properties = Map(
    "user"     -> source.username,
    "password" -> source.password
  )

  /**
   * Pre defined ZConnection Pools exist for: Postgres, SQL Server, Oracle,
   * MySQL and h2 custom pools, can also be constructed
   */
  val connectionPool: ZLayer[ZConnectionPoolConfig, Throwable, ZConnectionPool] =
    ZConnectionPool.postgres(source.host, source.port, source.database, properties)

  val layer: ZLayer[ZConnectionPoolConfig, Throwable, ZConnectionPool] = createZIOPoolConfig >>> connectionPool
  def commonDataService: CommonDataService =
    new PGDBCommonService(source, layer, createZIOPoolConfig)
}

object PGServicesFactory {

  def create(source: Source) =
    new PGServicesFactory(source.asInstanceOf[PGSource])
}
