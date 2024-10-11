package com.api.parking_control.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.service.ParkingSpotService;
import ParkingSpotDto.ParkingSpotDto;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RestController // indica para o framework que se trata de um controlador Rest, voltado para o desenvolvimento de aplicações web Restful e facilita que nós lidemos com requisições web (POST, GET, PUT, etc
@CrossOrigin(origins = "+", maxAge = 3600)
@RequestMapping("/parking-spot") //é uma anotação de nível de classe que define o prefixo de URL para todas as rotas de um controller

public class ParkingSpotController {

	final ParkingSpotService  parkingSpotService;
	
	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}
	
	@PostMapping                                     
	public ResponseEntity <Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {  
		if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
		   return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");		  
		}
		if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
			   return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
		}
		if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())){
			   return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict:Parking Spot already registered for this apartment/block!");
		}
		
		ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return  ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll((java.awt.print.Pageable) pageable));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if (!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if (!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
		}
		parkingSpotService.delete(parkingSpotModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted Sucessfully");	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, 
		                                            @RequestBody @ Valid ParkingSpotDto parkingSpotDto){ 
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if (!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
		}
		
		ParkingSpotModel parkingSpotModel =  parkingSpotModelOptional.get(); //realizar das duas maneiras
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
		parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
		
	}		
}
