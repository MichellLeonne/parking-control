package ParkingSpotDto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParkingSpotDto {
	
	@NotBlank
	private String parkingSpotNumber;
	@NotBlank
	@Size(max = 7)
	private String LicensePlateCar;
	@NotBlank
	private String brandCar;
	@NotBlank
	private String modelCar;
	@NotBlank
	private String colorCar;
	@NotBlank
	private LocalDateTime registrationDate;
	@NotBlank
	private String responsibleName;
	@NotBlank
	private String apartment;
	@NotBlank
	private String block;
	
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
	
	
	
}
