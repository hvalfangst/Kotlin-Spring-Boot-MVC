package hvalfangst.cars.model.requests

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate

data class UpsertOwnerRequest(
    @JsonProperty("owner_name")
    val ownerName: String,

    @JsonProperty("contact_info")
    val contactInfo: String,

    @JsonProperty("date_of_birth")
    val dateOfBirth: LocalDate
) : Serializable