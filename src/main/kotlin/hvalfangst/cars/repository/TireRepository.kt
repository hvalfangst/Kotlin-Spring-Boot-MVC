package hvalfangst.cars.repository

import hvalfangst.cars.model.Tire
import hvalfangst.cars.model.requests.UpsertTireRequest
import hvalfangst.cars.model.tables.TiresTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class TireRepository {

    fun listTires(): List<Tire> {
        return transaction {
            TiresTable.selectAll().map { it.toTire() }
        }
    }

    fun createTire(request: UpsertTireRequest): Int {
        var tireId = -1
        transaction {
            tireId = TiresTable.insert {
                it[carId] = request.carId
                it[brand] = request.brand
                it[size] = request.size
                it[manufacturingDate] = request.manufacturingDate
            } get TiresTable.id
        }
        return tireId
    }

    private fun ResultRow.toTire(): Tire {
        return Tire(
            id = this[TiresTable.id],
            carId = this[TiresTable.carId],
            brand = this[TiresTable.brand],
            size = this[TiresTable.size],
            manufacturingDate = this[TiresTable.manufacturingDate]
        )
    }

    fun getTiresForCar(carId: Int): List<Tire> {
        return transaction {
            TiresTable.select { TiresTable.carId eq carId }
                .map { it.toTire() }
        }
    }
}
