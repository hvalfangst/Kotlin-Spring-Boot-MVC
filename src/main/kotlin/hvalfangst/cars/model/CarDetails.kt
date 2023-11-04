package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class CarDetails(
    @JsonProperty("car")
    val car: Car,
    @JsonProperty("owner")
    val owner: Owner,
    @JsonProperty("tires")
    val tires: List<Tire>,
    @JsonProperty("insurance")
    val insurance: Insurance,
    @JsonProperty("repairs")
    val repairs: List<Repair>,
    @JsonProperty("engine")
    val engine: Engine
): Serializable
