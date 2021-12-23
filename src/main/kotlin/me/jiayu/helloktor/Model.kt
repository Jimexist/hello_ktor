package me.jiayu.helloktor

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Cities : IntIdTable() {
  val name = varchar("name", 50)
}

class City(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<City>(Cities)

  var name by Cities.name
}

@Serializable
data class CityResponse(val id: Int, val name: String)
