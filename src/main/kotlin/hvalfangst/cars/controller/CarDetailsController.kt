package hvalfangst.cars.controller

import hvalfangst.cars.model.CarDetails
import hvalfangst.cars.service.CarDetailsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/car-details")
class CarDetailsController(private val carDetailsService: CarDetailsService) {

    @GetMapping("/{carId}")
    fun getCarDetails(@PathVariable carId: Int): ResponseEntity<CarDetails> {
        val carDetails = carDetailsService.getCarDetails(carId)
        return ResponseEntity.ok(carDetails)
    }
}

