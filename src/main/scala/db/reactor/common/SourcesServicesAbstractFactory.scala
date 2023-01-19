package db.reactor.common

import db.reactor.config.Source
import db.reactor.pg.{PGServicesFactory, PGSourceType}
import zio.{ZIO, ZLayer}

class SourcesServicesAbstractFactory {

  def create[T <: SourceServicesFactory](source: Source): ZIO[Any, Exception, SourceServicesFactory] = source match {
    case _: PGSourceType => ZIO.succeed(PGServicesFactory.create(source))
    case _               => ZIO.fail(new Exception(s"Invalid source type ${source}"))
  }
}

object SourcesServicesAbstractFactory {
  val layer: ZLayer[Any, Nothing, SourcesServicesAbstractFactory] =
    ZLayer.succeed(new SourcesServicesAbstractFactory())
}
