package hvalfangst.cars.repository

import hvalfangst.cars.model.Insurance
import hvalfangst.cars.model.requests.UpsertInsuranceRequest
import hvalfangst.cars.model.tables.InsuranceTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class InsuranceRepository {

    fun listInsurance(): List<Insurance> {
        return transaction {
            InsuranceTable.selectAll().map { it.toInsurance() }
        }
    }

    fun createInsurance(request: UpsertInsuranceRequest): Int {
        var insuranceId: Int = -1
        transaction {
            insuranceId = InsuranceTable.insert {
                it[carId] = request.carId
                it[policyNumber] = request.policyNumber
                it[provider] = request.provider
                it[startDate] = request.startDate
                it[endDate] = request.endDate
            } get InsuranceTable.id
        }
        return insuranceId
    }

    private fun ResultRow.toInsurance(): Insurance {
        return Insurance(
            id = this[InsuranceTable.id],
            car_id = this[InsuranceTable.carId],
            policy_number = this[InsuranceTable.policyNumber],
            provider = this[InsuranceTable.provider],
            start_date = this[InsuranceTable.startDate],
            end_date = this[InsuranceTable.endDate]
        )
    }

    fun getInsuranceForCar(carId: Int): Insurance {
        return transaction {
            InsuranceTable.select { InsuranceTable.carId eq carId }
                .map { it.toInsurance() }
                .singleOrNull() ?: throw NoSuchElementException("Insurance not found for ID: $carId")
        }
    }
}
