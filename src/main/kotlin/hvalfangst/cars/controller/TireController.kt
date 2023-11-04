package hvalfangst.cars.controller

import hvalfangst.cars.messages.Messages.TIRE_CREATED
import hvalfangst.cars.model.Tire
import hvalfangst.cars.model.requests.UpsertTireRequest
import hvalfangst.cars.repository.TireRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tires")
class TireController(private val tireRepository: TireRepository) {

    @GetMapping
    fun listTires(): ResponseEntity<List<Tire>> = ResponseEntity.ok(tireRepository.listTires())

    @PostMapping
    fun createTire(@RequestBody request: UpsertTireRequest): ResponseEntity<String> {
        val id = tireRepository.createTire(request)
        return ResponseEntity.status(TIRE_CREATED.status).body(TIRE_CREATED.message(id))
    }
}
