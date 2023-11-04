package hvalfangst.cars.repository

import hvalfangst.cars.model.Engine
import hvalfangst.cars.model.requests.UpsertEngineRequest
import hvalfangst.cars.model.tables.EnginesTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class EngineRepository {

    fun listEngines(): List<Engine> {
        return transaction {
            EnginesTable.selectAll().map { it.toEngine() }
        }
    }

    fun createEngine(request: UpsertEngineRequest): Int {
        var engineId: Int = -1
        transaction {
            engineId = EnginesTable.insert {
                it[carId] = request.carId
                it[type] = request.type
                it[displacement] = request.displacement
                it[horsepower] = request.horsepower
                it[manufacturingDate] = request.manufacturingDate
            } get EnginesTable.id
        }
        return engineId
    }

    private fun ResultRow.toEngine(): Engine {
        return Engine(
            id = this[EnginesTable.id],
            carId = this[EnginesTable.carId],
            type = this[EnginesTable.type],
            displacement = this[EnginesTable.displacement],
            horsepower = this[EnginesTable.horsepower],
            manufacturingDate = this[EnginesTable.manufacturingDate]
        )
    }

    fun getEngineForCar(carId: Int): Engine {
        return transaction {
            EnginesTable.select { EnginesTable.carId eq carId }
                .map { it.toEngine() }
                .singleOrNull() ?: throw NoSuchElementException("Car not found for ID: $carId")
        }
    }
}
