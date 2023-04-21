import db.reactor.common.dto.PGTableMeta
import db.reactor.config._
import zio._
import zio.test._

object MetaDataSpec extends ZIOSpecDefault with PGTestHelper {
  val sources: List[Source] = List(
    PGSource(
      host = "localhost",
      port = 5445,
      name = "test base",
      database = "test_db",
      username = "test",
      password = "test",
      tables = List(Table(name = "test_table", fields = List(Field(name = "test", `type` = "string"))))
    )
  )

  def spec = suite("clock")(
    test("foo") {
      // TODO адекватно скомпоновать инициализацию таблиц
      for {
        _ <- setUpDB()
        _ <- executeStatement(
               """INSERT INTO
                 |test_table
                 |VALUES (1, 'Abc', '{"key": "val"}', '2022-07-07', true, '2022-07-07', '2022-07-07')
                 |""".stripMargin
             )
        sources <- ZIO.service[List[Source]]
        meta    <- ZIO.foreach(sources)(s => s.sourceServiceF().commonDataService.selectSourceData)
        _       <- drop
      } yield (assertTrue({
        val pgSource = meta.head.asInstanceOf[PGTableMeta]
        pgSource.name == "test base" && pgSource.rowsCount == 1
      }))
    }
  ).provide(ZLayer.succeed(sources))
}
