package hvalfangst.cars.messages

import org.springframework.http.HttpStatus

enum class Messages(val status: HttpStatus, val message: (id: Int) -> String) {
    OWNER_CREATED(HttpStatus.CREATED, { id -> "Owner with ID $id has been created" }),
    OWNER_UPDATED(HttpStatus.OK, { id -> "Owner with ID $id has been updated" }),
    OWNER_DELETED(HttpStatus.NO_CONTENT, { id -> "Owner with ID $id has been deleted" }),

    CAR_CREATED(HttpStatus.CREATED, { id -> "Car with ID $id has been created" }),
    REPAIR_CREATED(HttpStatus.CREATED, { id -> "Repair with ID $id has been created" }),
    ENGINE_CREATED(HttpStatus.CREATED, { id -> "Engine with ID $id has been created" }),
    INSURANCE_CREATED(HttpStatus.CREATED, { id -> "Insurance with ID $id has been created" }),
    TIRE_CREATED(HttpStatus.CREATED, { id -> "Tire with ID $id has been  created" }),
}