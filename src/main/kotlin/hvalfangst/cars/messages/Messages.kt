package hvalfangst.cars.messages

import org.springframework.http.HttpStatus

enum class Messages(val status: HttpStatus, val message: (id: Int) -> String) {
    OWNER_CREATED(HttpStatus.CREATED, { id -> "Owner with ID $id created" }),
    CAR_CREATED(HttpStatus.CREATED, { id -> "Car with ID $id created" }),
    REPAIR_CREATED(HttpStatus.CREATED, { id -> "Repair with ID $id created" }),
    ENGINE_CREATED(HttpStatus.CREATED, { id -> "Engine with ID $id created" }),
    INSURANCE_CREATED(HttpStatus.CREATED, { id -> "Insurance with ID $id created" }),
    TIRE_CREATED(HttpStatus.CREATED, { id -> "Tire with ID $id created" }),
}