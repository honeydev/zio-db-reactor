import zio._
import zio.jdbc._

trait PGTestHelper {
  val createZIOPoolConfig =
    ZLayer.succeed(ZConnectionPoolConfig.default)

  val properties = Map(
    "user"     -> "test",
    "password" -> "test"
  )

  val connectionPool =
    ZConnectionPool.postgres("localhost", 5445, "test_db", properties)

  val Slayer = createZIOPoolConfig >>> connectionPool

  def setUpDB() =
    executeStatement("""
          CREATE TABLE IF NOT EXISTS test_table(
            id               BIGSERIAL PRIMARY KEY,
            varchar          VARCHAR  NOT NULL,
            json             JSON  NOT NULL,
            date             DATE NOT NULL,
            bool             BOOLEAN NOT NULL                   DEFAULT true,
            created_at       TIMESTAMPTZ NOT NULL               DEFAULT current_timestamp,
            updated_at       TIMESTAMPTZ NOT NULL               DEFAULT current_timestamp
        )""")

  def drop =
    executeStatement("DROP TABLE test_table")

  def executeStatement(sql: String) =
    (for {
      result <- transaction(execute(sql))
    } yield result).provideLayer(Slayer)
}
