package hvalfangst.cars.repository

import hvalfangst.cars.model.Car
import hvalfangst.cars.model.requests.UpsertCarRequest
import hvalfangst.cars.model.tables.CarsTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class CarRepository {

    fun listCars(): List<Car> {
        return transaction {
            CarsTable.selectAll().map { it.toCar() }
        }
    }

    fun createCar(request: UpsertCarRequest): Int {
        var carId: Int = -1
        transaction {
            carId = CarsTable.insert {
                it[make] = request.make
                it[model] = request.model
                it[year] = request.year
                it[vin] = request.vin
                it[color] = request.color
                it[purchaseDate] = request.purchaseDate
                it[ownerId] = request.ownerId
            } get CarsTable.id
        }
        return carId
    }

    private fun ResultRow.toCar(): Car {
        return Car(
            id = this[CarsTable.id],
            make = this[CarsTable.make],
            model = this[CarsTable.model],
            year = this[CarsTable.year],
            vin = this[CarsTable.vin],
            color = this[CarsTable.color],
            purchaseDate = this[CarsTable.purchaseDate],
            ownerId = this[CarsTable.ownerId]
        )
    }

    fun getCarById(carId: Int): Car {
        return transaction {
            CarsTable.select { CarsTable.id eq carId }
                .map { it.toCar() }
                .singleOrNull() ?: throw NoSuchElementException("Car not found for ID: $carId")
        }
    }
}
