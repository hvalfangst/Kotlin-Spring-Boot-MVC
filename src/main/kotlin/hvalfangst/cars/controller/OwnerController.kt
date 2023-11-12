package hvalfangst.cars.controller

import hvalfangst.cars.messages.Messages.OWNER_CREATED
import hvalfangst.cars.messages.Messages.OWNER_UPDATED
import hvalfangst.cars.messages.Messages.OWNER_DELETED
import hvalfangst.cars.model.Owner
import hvalfangst.cars.model.requests.UpsertOwnerRequest
import hvalfangst.cars.repository.OwnerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/owners")
class OwnerController(private val ownerRepository: OwnerRepository) {

    @PostMapping
    fun createOwner(@RequestBody request: UpsertOwnerRequest): ResponseEntity<String> {
        val id = ownerRepository.createOwner(request)
        return ResponseEntity.status(OWNER_CREATED.status).body(OWNER_CREATED.message(id))
    }

    @PutMapping("/{id}")
    fun updateOwner(@PathVariable id: Int, @RequestBody request: UpsertOwnerRequest): ResponseEntity<String> {
        ownerRepository.updateOwner(id, request)
        return ResponseEntity.status(OWNER_UPDATED.status).body(OWNER_UPDATED.message(id))
    }

    @GetMapping("/{id}")
    fun getOwner(@PathVariable id: Int): ResponseEntity<Owner> {
        val owner = ownerRepository.getOwnerById(id)
        return ResponseEntity.status(HttpStatus.OK).body(owner)
    }

    @GetMapping
    fun listOwners(): ResponseEntity<List<Owner>> = ResponseEntity.ok(ownerRepository.listOwners())

    @DeleteMapping("/{id}")
    fun deleteOwner(@PathVariable id: Int): ResponseEntity<String> {
        ownerRepository.deleteOwnerById(id)
        return ResponseEntity.status(OWNER_DELETED.status).body(OWNER_DELETED.message(id))
    }

}