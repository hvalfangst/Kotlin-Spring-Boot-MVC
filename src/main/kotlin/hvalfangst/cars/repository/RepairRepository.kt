package hvalfangst.cars.repository

import hvalfangst.cars.model.Repair
import hvalfangst.cars.model.requests.UpsertRepairRequest
import hvalfangst.cars.model.tables.RepairsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class RepairRepository {

    fun listRepairs(): List<Repair> {
        return transaction {
            RepairsTable.selectAll().map { it.toRepair() }
        }
    }

    fun createRepair(request: UpsertRepairRequest): Int {
        var repairId: Int = -1
        transaction {
            repairId = RepairsTable.insert {
                it[carId] = request.carId
                it[repairType] = request.repairType
                it[repairDate] = request.repairDate
                it[cost] = request.cost
            } get RepairsTable.id
        }
        return repairId
    }

    private fun ResultRow.toRepair(): Repair {
        return Repair(
            id = this[RepairsTable.id],
            carId = this[RepairsTable.carId],
            repairType = this[RepairsTable.repairType],
            repairDate = this[RepairsTable.repairDate],
            cost = this[RepairsTable.cost]
        )
    }

    fun getRepairsForCar(carId: Int): List<Repair> {
        return transaction {
            RepairsTable.select { RepairsTable.carId eq carId }
                .map { it.toRepair() }
        }
    }
}