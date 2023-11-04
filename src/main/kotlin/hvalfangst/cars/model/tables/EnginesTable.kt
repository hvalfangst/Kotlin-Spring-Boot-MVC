package hvalfangst.cars.model.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object EnginesTable : Table("engines") {
    val id = integer("id").autoIncrement()
    val carId = integer("car_id") references CarsTable.id
    val type = text("type")
    val displacement = text("displacement")
    val horsepower = integer("horsepower")
    val manufacturingDate = date("manufacturing_date")
}