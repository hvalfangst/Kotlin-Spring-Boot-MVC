package hvalfangst.cars.controller

import hvalfangst.cars.messages.Messages.ENGINE_CREATED
import hvalfangst.cars.model.Car
import hvalfangst.cars.model.Engine
import hvalfangst.cars.model.requests.UpsertEngineRequest
import hvalfangst.cars.repository.EngineRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/engines")
class EngineController(private val engineRepository: EngineRepository) {

    @GetMapping
    fun listEngines(): ResponseEntity<List<Engine>> = ResponseEntity.ok(engineRepository.listEngines())

    @GetMapping("/{carId}")
    fun getEngineByCarId(@PathVariable carId: Int): ResponseEntity<Engine> {
        val engine = engineRepository.getEngineForCar(carId)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(engine)
    }

    @PostMapping
    fun createEngine(@RequestBody request: UpsertEngineRequest): ResponseEntity<String> {
        val id = engineRepository.createEngine(request)
        return ResponseEntity.status(ENGINE_CREATED.status).body(ENGINE_CREATED.message(id))
    }
}
