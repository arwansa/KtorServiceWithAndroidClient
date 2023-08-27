package me.arwan
//
//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import io.ktor.http.*
//import io.ktor.server.testing.*
//import kotlin.test.*
//import me.arwan.plugins.*
//
//class ApplicationTest {
//    @Test
//    fun testRoot() = testApplication {
//        application {
//            configureCalculatorRouting()
//        }
//        client.get("/").apply {
//            assertEquals(HttpStatusCode.OK, status)
//            assertEquals("Hello World!", bodyAsText())
//        }
//    }
//}
