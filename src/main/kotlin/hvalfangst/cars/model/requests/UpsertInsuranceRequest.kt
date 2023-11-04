package hvalfangst.cars.model.requests

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate

data class UpsertInsuranceRequest(
    @JsonProperty("car_id")
    val carId: Int,

    @JsonProperty("policy_number")
    val policyNumber: String,

    @JsonProperty("provider")
    val provider: String,

    @JsonProperty("start_date")
    val startDate: LocalDate,

    @JsonProperty("end_date")
    val endDate: LocalDate
) : Serializable