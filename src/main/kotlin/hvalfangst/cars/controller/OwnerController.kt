package hvalfangst.cars.controller

import hvalfangst.cars.messages.Messages.OWNER_CREATED
import hvalfangst.cars.model.Owner
import hvalfangst.cars.model.requests.UpsertOwnerRequest
import hvalfangst.cars.repository.OwnerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/owners")
class OwnerController(private val ownerRepository: OwnerRepository) {

    @GetMapping
    fun listOwners(): ResponseEntity<List<Owner>> = ResponseEntity.ok(ownerRepository.listOwners())

    @PostMapping
    fun createOwner(@RequestBody request: UpsertOwnerRequest): ResponseEntity<String> {
        val id = ownerRepository.createOwner(request)
        return ResponseEntity.status(OWNER_CREATED.status).body(OWNER_CREATED.message(id))
    }

}