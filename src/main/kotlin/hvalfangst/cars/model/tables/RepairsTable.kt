package hvalfangst.cars.model.tables

import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.Table

object RepairsTable : Table("repairs") {
    val id = integer("id").autoIncrement()
    val carId = integer("car_id") references CarsTable.id
    val repairType = text("repair_type")
    val repairDate = date("repair_date")
    val cost = decimal("cost", 10, 2)
}