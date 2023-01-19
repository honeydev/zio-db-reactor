import zio.test._

object HelloWorldSpec extends ZIOSpecDefault {
  def spec = suite("clock")(
    test("foo") {
      assertTrue(true)
    },
    test("foo bar") {
      assertTrue(true)
    },
    test("foo bar baz") {
      assertTrue(true)
    }
  )
}
