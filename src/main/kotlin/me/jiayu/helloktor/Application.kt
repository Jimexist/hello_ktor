package me.jiayu.helloktor

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
  val db = DbSettings.db
  embeddedServer(Netty, port = 8080) {
    install(ContentNegotiation) { json() }
    routing {
      get("/") { call.respondText("Hello, world!") }

      get("/cities") {
        val cities =
            transaction { City.all().toList() }.map {
              CityResponse(id = it.id.value, name = it.name)
            }
        call.respond(cities)
      }
    }
  }
      .start(wait = true)
}

object DbSettings {
  val db by lazy {
    // an example connection to H2 DB
    val db = Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
    transaction {
      // print sql to std-out
      addLogger(StdOutSqlLogger)
      SchemaUtils.create(Cities)
      // insert new city. SQL: INSERT INTO Cities (name) VALUES ('St. Petersburg')
      val stPeteId = Cities.insert { it[name] = "St. Petersburg" } get Cities.id
    }
    db
  }
}
