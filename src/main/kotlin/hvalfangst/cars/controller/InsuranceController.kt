package hvalfangst.cars.controller

import hvalfangst.cars.messages.Messages.INSURANCE_CREATED
import hvalfangst.cars.model.Insurance
import hvalfangst.cars.model.requests.UpsertInsuranceRequest
import hvalfangst.cars.repository.InsuranceRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/insurance")
class InsuranceController(private val insuranceRepository: InsuranceRepository) {

    @GetMapping
    fun listInsurance(): ResponseEntity<List<Insurance>> = ResponseEntity.ok(insuranceRepository.listInsurance())

    @PostMapping
    fun createInsurance(@RequestBody request: UpsertInsuranceRequest): ResponseEntity<String> {
        val id = insuranceRepository.createInsurance(request)
        return ResponseEntity.status(INSURANCE_CREATED.status).body(INSURANCE_CREATED.message(id))
    }
}
