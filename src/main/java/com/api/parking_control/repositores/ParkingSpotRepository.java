package com.api.parking_control.repositores;
import java.awt.print.Pageable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.parking_control.models.ParkingSpotModel;

@Repository               //a classe fornece o mecanismo para operação de armazenamento, recuperação, atualização, exclusão e pesquisa em objetos
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

	boolean existsByLicensePlateCar(String licensePlateCar);
	boolean existsByParkingSpotNumber(String parkingSpotNumber);
	boolean existsByApartmentAndBlock(String apartment, String block);
	Page<ParkingSpotModel> findAll(Pageable pageable);


}
