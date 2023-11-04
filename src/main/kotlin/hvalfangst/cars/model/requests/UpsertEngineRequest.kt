package hvalfangst.cars.model.requests

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate

data class UpsertEngineRequest(
    @JsonProperty("car_id")
    val carId: Int,

    @JsonProperty("type")
    val type: String,

    @JsonProperty("displacement")
    val displacement: String,

    @JsonProperty("horsepower")
    val horsepower: Int,

    @JsonProperty("manufacturing_date")
    val manufacturingDate: LocalDate
) : Serializable
