package hvalfangst.cars.service

import hvalfangst.cars.model.CarDetails
import hvalfangst.cars.repository.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CarDetailsService(
    private val carRepository: CarRepository,
    private val ownerRepository: OwnerRepository,
    private val tireRepository: TireRepository,
    private val insuranceRepository: InsuranceRepository,
    private val repairRepository: RepairRepository,
    private val engineRepository: EngineRepository
) {

    fun getCarDetails(carId: Int): CarDetails {
        val car = carRepository.getCarById(carId)
        val owner = ownerRepository.getOwnerById(car.ownerId)
        val tires = tireRepository.getTiresForCar(carId)
        val insurance = insuranceRepository.getInsuranceForCar(carId)
        val repairs = repairRepository.getRepairsForCar(carId)
        val engine = engineRepository.getEngineForCar(carId)

        return CarDetails(car, owner, tires, insurance, repairs, engine)
    }
}