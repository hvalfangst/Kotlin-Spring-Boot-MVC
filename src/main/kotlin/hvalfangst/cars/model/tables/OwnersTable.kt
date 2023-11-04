package hvalfangst.cars.model.tables

import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.Table

object OwnersTable : Table("owners") {
    val id = integer("id").autoIncrement()
    val ownerName = text("owner_name")
    val contactInfo = text("contact_info")
    val dateOfBirth = date("date_of_birth")
}