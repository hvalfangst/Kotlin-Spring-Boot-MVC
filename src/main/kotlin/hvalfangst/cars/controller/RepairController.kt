package hvalfangst.cars.controller

import hvalfangst.cars.messages.Messages.REPAIR_CREATED
import hvalfangst.cars.model.Repair
import hvalfangst.cars.model.requests.UpsertRepairRequest
import hvalfangst.cars.repository.RepairRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/repairs")
class RepairController(private val repairRepository: RepairRepository) {

    @GetMapping
    fun listRepairs(): ResponseEntity<List<Repair>> = ResponseEntity.ok(repairRepository.listRepairs())

    @PostMapping
    fun createRepair(@RequestBody request: UpsertRepairRequest): ResponseEntity<String> {
        val id = repairRepository.createRepair(request)
        return ResponseEntity.status(REPAIR_CREATED.status).body(REPAIR_CREATED.message(id))
    }
}
