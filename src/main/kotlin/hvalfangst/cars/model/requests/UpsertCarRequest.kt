package hvalfangst.cars.model.requests

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate

data class UpsertCarRequest(
    @JsonProperty("make")
    val make: String,

    @JsonProperty("model")
    val model: String,

    @JsonProperty("year")
    val year: Int,

    @JsonProperty("vin")
    val vin: String,

    @JsonProperty("color")
    val color: String,

    @JsonProperty("purchase_date")
    val purchaseDate: LocalDate,

    @JsonProperty("owner_id")
    val ownerId: Int
) : Serializable