
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class StressTest extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")

	val headers_0 = Map("Pragma" -> "no-cache")

	val headers_2 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_3 = Map("Accept" -> "text/css,*/*;q=0.1")

    val uri2 = "http://detectportal.firefox.com/success.txt"

	val scn = scenario("StressTest")
		.exec(http("request_0")
			.get(uri2 + "")
			.headers(headers_0))
		.pause(3)
		.exec(http("request_1")
			.get(uri2 + "")
			.headers(headers_0))
		.pause(2)
		.exec(http("request_2")
			.get("/")
			.headers(headers_2)
			.resources(http("request_3")
			.get("/signin.css")
			.headers(headers_3)))
		.pause(7)
		.exec(http("request_4")
			.post("/userForm;jsessionid=83361648CB913AAD41D80750E787A6F1")
			.headers(headers_2)
			.formParam("login", "Prueba01")
			.formParam("password", "123456")
			.formParam("kind", "PERSON")
			.formParam("Enter", "enter")
			.resources(http("request_5")
			.get("/signin.css")
			.headers(headers_3),
            http("request_6")
			.get("/bootstrap.min.js")
			.check(status.is(404)),
            http("request_7")
			.get("/bootstrap.min.js")
			.check(status.is(404))))
		.pause(2)
		.exec(http("request_8")
			.get("/passMenu")
			.headers(headers_2)
			.resources(http("request_9")
			.get("/signin.css")
			.headers(headers_3)))

	setUp(scn.inject(rampUsers(1000) over(60 seconds))).protocols(httpProtocol)
}