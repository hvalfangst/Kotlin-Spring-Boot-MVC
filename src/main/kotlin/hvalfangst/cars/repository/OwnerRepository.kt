package hvalfangst.cars.repository

import hvalfangst.cars.model.Owner
import hvalfangst.cars.model.requests.UpsertOwnerRequest
import hvalfangst.cars.model.tables.OwnersTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class OwnerRepository {

    fun listOwners(): List<Owner> {
        return transaction {
            OwnersTable.selectAll().map { it.toOwner() }
        }
    }

    fun createOwner(request: UpsertOwnerRequest): Int {
        var ownerId: Int = -1
        transaction {
            ownerId = OwnersTable.insert {
                it[ownerName] = request.ownerName
                it[contactInfo] = request.contactInfo
                it[dateOfBirth] = request.dateOfBirth
            } get OwnersTable.id
        }

        return ownerId
    }

    private fun ResultRow.toOwner(): Owner {
        return Owner(
            ownerId = this[OwnersTable.id],
            ownerName = this[OwnersTable.ownerName],
            contactInfo = this[OwnersTable.contactInfo],
            dateOfBirth = this[OwnersTable.dateOfBirth]
        )
    }

    fun getOwnerById(ownerId: Int): Owner {
        return transaction {
            OwnersTable.select { OwnersTable.id eq ownerId }
                .map { it.toOwner() }
                .singleOrNull() ?: throw NoSuchElementException("Car not found for ID: $ownerId")
        }
    }
}