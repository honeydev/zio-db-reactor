package db.reactor.common

import db.reactor.common.dto.SourceMeta
import zio._

trait CommonDataService {
  def selectSourceData: ZIO[Any, Throwable, SourceMeta]
}
