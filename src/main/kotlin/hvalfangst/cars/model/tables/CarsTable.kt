package hvalfangst.cars.model.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object CarsTable : Table("cars") {
    val id = integer("id").autoIncrement()
    val make = text("make")
    val model = text("model")
    val year = integer("year")
    val vin = text("vin").uniqueIndex()
    val color = text("color")
    val purchaseDate = date("purchase_date")
    val ownerId = integer("owner_id") references OwnersTable.id
}
