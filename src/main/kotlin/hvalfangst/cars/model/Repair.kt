package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

data class Repair(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("car_id")
    val carId: Int,

    @JsonProperty("repair_type")
    val repairType: String,

    @JsonProperty("repair_date")
    val repairDate: LocalDate,

    @JsonProperty("cost")
    val cost: BigDecimal
) : Serializable