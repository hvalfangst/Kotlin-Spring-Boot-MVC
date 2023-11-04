package hvalfangst.cars.model.tables

import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.Table

object TiresTable : Table("tires") {
    val id = integer("id").autoIncrement()
    val carId = integer("car_id") references CarsTable.id
    val brand = text("brand")
    val size = text("size")
    val manufacturingDate = date("manufacturing_date")
}