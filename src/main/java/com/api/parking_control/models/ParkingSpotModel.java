package com.api.parking_control.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                                                        // é utilizada para informar que uma classe também é uma entidade.
@Table(name = "TB_PARKING_SPOT")                               // Tabela a ser gerada no BD
public class ParkingSpotModel implements Serializable{ 
	private static final long serialVersionUID = 1L;           // é quando um objeto é transformado, em umacadeia de bytes e desta forma pode ser manipulado de maneira mais fácil, seja através de transporte pela rede ou salvo no disco.
	
	@Id                                                        // definir tipo Identificador
	@GeneratedValue(strategy = GenerationType.AUTO)            // GenerationType.AUTO = gerar ID de forma automatica
	private UUID id;                                           // Identificador
	@Column(nullable = false, unique = true, length = 10)      //nullable = se é do tipo nullo - unique =  se é unico  length = tamanho caracter
	private String parkingSpotNumber;
	@Column(nullable = false, unique = true, length = 7)
	private String LicensePlateCar;
	@Column(nullable = false, length = 70)
	private String brandCar;
	@Column(nullable = false, length = 70)
	private String modelCar;
	@Column(nullable = false, length = 70)
	private String colorCar;
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	@Column(nullable = false, length = 130)
	private String responsibleName;
	@Column(nullable = false, length = 30)
	private String apartment;
	@Column(nullable = false, length = 30)
	private String block;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getParkingSpotNumber() {
		return parkingSpotNumber;
	}
	public void setParkingSpotNumber(String parkingSpotNumber) {
		this.parkingSpotNumber = parkingSpotNumber;
	}
	public String getLicensePlateCar() {
		return LicensePlateCar;
	}
	public void setLicensePlateCar(String licensePlateCar) {
		LicensePlateCar = licensePlateCar;
	}
	public String getBrandCar() {
		return brandCar;
	}
	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}
	public String getModelCar() {
		return modelCar;
	}
	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}
	public String getColorCar() {
		return colorCar;
	}
	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
	}
	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getResponsibleName() {
		return responsibleName;
	}
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
