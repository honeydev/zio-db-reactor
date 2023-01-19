package db.reactor

import db.reactor.common.SourcesServicesAbstractFactory
import db.reactor.config.Source
import db.reactor.config.Source.Sources
import zhttp.http._
import zhttp.service.Server
import zio._
import zio.json._

import java.net.InetAddress

trait DBReactor extends ZIOAppDefault {

  val host: InetAddress
  val port: Int
  val sources: List[Source]

  // Create HTTP route
  val app: Http[Sources, Throwable, Request, Response] =
    Http.collectZIO[Request] { case Method.GET -> !! / "api" / "sources" =>
      for {
        meta <- ZIO.foreach(sources)(s => s.sourceServiceF().commonDataService.selectSourceData)
        _    <- Console.printLine(s"${meta}")
      } yield Response.json(meta.toJson)
    }
  def run =
    Server
      .start(address = host, port = port, http = app)
      .provide(
        ZLayer.succeed(sources),
        SourcesServicesAbstractFactory.layer
      )
      .debug("results")
      .exitCode
}
