import db.reactor.DBReactor
import db.reactor.config._

import java.net.InetAddress
import scala.language.postfixOps

object DevApp extends DBReactor {

  val host: InetAddress = InetAddress.getByName("localhost")
  val port: Int         = 8090
  val sources: List[Source] = List(
    PGSource(
      host = "localhost",
      port = 5444,
      database = "test_db",
      username = "test",
      password = "test",
      tables = List(Table(name = "example", fields = List(Field(name = "test", `type` = "string"))))
    ),
    PGSource(
      host = "localhost",
      port = 5445,
      database = "test_db",
      username = "test",
      password = "test",
      tables = List(Table(name = "example", fields = List(Field(name = "test", `type` = "string"))))
    )
  )
}
