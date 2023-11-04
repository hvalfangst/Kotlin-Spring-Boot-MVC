package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate

data class Insurance(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("carId")
    val car_id: Int,

    @JsonProperty("policyNumber")
    val policy_number: String,

    @JsonProperty("provider")
    val provider: String,

    @JsonProperty("startDate")
    val start_date: LocalDate,

    @JsonProperty("endDate")
    val end_date: LocalDate
) : Serializable