package db.reactor.common.dto

trait Response
case class Success(status: String = "success", response: Response)
