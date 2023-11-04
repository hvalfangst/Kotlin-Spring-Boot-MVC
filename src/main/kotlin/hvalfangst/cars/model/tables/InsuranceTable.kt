package hvalfangst.cars.model.tables

import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.Table

object InsuranceTable : Table("insurance") {
    val id = integer("id").autoIncrement()
    val carId = integer("car_id") references CarsTable.id
    val policyNumber = text("policy_number")
    val provider = text("provider")
    val startDate = date("start_date")
    val endDate = date("end_date")
}