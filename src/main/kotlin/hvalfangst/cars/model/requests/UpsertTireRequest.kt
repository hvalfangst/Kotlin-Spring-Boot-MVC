package hvalfangst.cars.model.requests

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate

data class UpsertTireRequest(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("car_id")
    val carId: Int,

    @JsonProperty("brand")
    val brand: String,

    @JsonProperty("size")
    val size: String,

    @JsonProperty("manufacturing_date")
    val manufacturingDate: LocalDate
) : Serializable