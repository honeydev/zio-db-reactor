package db.reactor.common.dto

import zio.json._

sealed trait SourceMeta

object SourceMeta {
  implicit val decoder: JsonDecoder[SourceMeta] =
    DeriveJsonDecoder.gen[SourceMeta]

  implicit val encoder: JsonEncoder[SourceMeta] =
    DeriveJsonEncoder.gen[SourceMeta]
}

case class PGTableMeta(name: String, rowsCount: Long) extends SourceMeta {
  implicit val encoder: JsonEncoder[PGTableMeta] = DeriveJsonEncoder.gen[PGTableMeta]
}
