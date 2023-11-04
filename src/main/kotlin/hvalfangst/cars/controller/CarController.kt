package hvalfangst.cars.controller

import hvalfangst.cars.messages.Messages.CAR_CREATED
import hvalfangst.cars.model.Car
import hvalfangst.cars.model.requests.UpsertCarRequest
import hvalfangst.cars.repository.CarRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarController(private val carRepository: CarRepository) {

    @GetMapping
    fun listCars(): ResponseEntity<List<Car>> = ResponseEntity.ok(carRepository.listCars())

    @PostMapping
    fun createCar(@RequestBody request: UpsertCarRequest): ResponseEntity<String> {
        val id = carRepository.createCar(request)
        return ResponseEntity.status(CAR_CREATED.status).body(CAR_CREATED.message(id))
    }

}
