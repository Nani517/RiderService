package com.Rapido.RiderService.Ridercontorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Rapido.RiderService.DTO.Cordinate;
import com.Rapido.RiderService.DTO.CreateRiderAccount;
import com.Rapido.RiderService.DTO.Responsestructure;
import com.Rapido.RiderService.DTO.VehicleDTO;
import com.Rapido.RiderService.Service.RiderServicelayer;
import com.Rapido.RiderService.entity.Rider;
import com.Rapido.RiderService.entity.Vehicle;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class Ridercontorller {
	@Autowired
	private RiderServicelayer riderServicelayer;

	@PostMapping("/rider/createaccount")
	public Responsestructure<Rider> CreateRiderAccount(@RequestBody CreateRiderAccount cra) {
		return riderServicelayer.CreateAccountOfRider(cra);
	}

	@PatchMapping("/rider/updatevehicle")
	public Responsestructure<Vehicle> updateVehicle(@RequestParam int id, @RequestBody VehicleDTO vehicleDTO) {
		return riderServicelayer.UpdateVehicle(id, vehicleDTO);
	}

	@DeleteMapping("/rider/deleteAccount")
	public Responsestructure<Rider> delectoperation(@RequestParam int id) {
		return riderServicelayer.deleteRiderAccount(id);
	}

	@GetMapping("/rider/findrider")
	public Responsestructure<Rider> findByid(@RequestParam int id) {
		return riderServicelayer.findById(id);
	}

	@PutMapping("/rider/updatestatus")
	public Responsestructure<Rider> checkstatus(@RequestParam int id) {
		return riderServicelayer.ChangeTheStatus(id);
	}

	@GetMapping("/rider/sendcordinatelocation")
	public void sendcordinate(@RequestParam int riderid, @RequestParam String vehicletype,@RequestBody Cordinate cordinate) {
		riderServicelayer.SendCordinateLocation(riderid, vehicletype, cordinate);
	}
	@GetMapping("/rider/getallAssingedride")
	public List<String> AssignedRide(@RequestParam int riderid,@RequestParam String vehicleType) {
		return riderServicelayer.FindAllAssingedride(riderid,vehicleType);
	}
	@PostMapping("/rider/acceptingbooking")
	public void Acceptbooking(@RequestParam int riderid ,@RequestParam int bookingid) {
		riderServicelayer.acceptingBooking(riderid,bookingid);
	}
	
	@GetMapping("/rider/movetowardspickup")
	public void moveTowardsPickup(@RequestParam int bid, @RequestParam double latitude, @RequestParam double longtitude, HttpServletResponse resp) {
		riderServicelayer.moveTowardsPickup(bid, latitude, longtitude, resp);
	}
	
	
	
	
	

}
