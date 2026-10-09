package com.Rapido.RiderService.Ridercontorller;

import java.io.IOException;
import java.net.http.HttpResponse;
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

import com.Rapido.RiderService.DTO.AssignedRideDTO;
import com.Rapido.RiderService.DTO.Cordinate;
import com.Rapido.RiderService.DTO.CreateRiderAccount;
import com.Rapido.RiderService.DTO.Responsestructure;
import com.Rapido.RiderService.DTO.RiderHistoryDTO;
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
	public void sendcordinate(@RequestParam int riderid, @RequestParam String vehicletype,
			@RequestBody Cordinate cordinate) {
		riderServicelayer.SendCordinateLocation(riderid, vehicletype, cordinate);
	}

	@GetMapping("/rider/getallAssingedride")
	public List<AssignedRideDTO> AssignedRide(@RequestParam int riderid) {
		return riderServicelayer.FindAllAssingedride(riderid);
	}

	@PostMapping("/rider/acceptingbooking")
	public Responsestructure<String> Acceptbooking(@RequestParam int riderid, @RequestParam int bookingid) {
		return riderServicelayer.acceptingBooking(bookingid, riderid);
	}

	@GetMapping("/ride/movetowardspickuplocation")
	public void movingtopicpuplocation(@RequestParam int bookingid,HttpServletResponse response) throws IOException{
	    String googleMapsUrl = riderServicelayer.ridermovingtopicpuplocation(bookingid);
	    response.sendRedirect(googleMapsUrl);
	}
	@PutMapping("/rider/updatebookingstatus")
	public Responsestructure<String> updatingthebookingStatus(@RequestParam int bookingid) {
		return riderServicelayer.updatebookingStatus(bookingid);
	}
	@GetMapping("/rider/otpverification")
	public void optverify(@RequestParam String otp , @RequestParam int bookingid) {
		riderServicelayer.OTPverification(otp,bookingid);
	}
	@GetMapping("/ride/movetowardsdroplocation")
	public void movingtodroplocation(@RequestParam int bookingid ,HttpServletResponse response) throws IOException {
		String googleMapUrl =riderServicelayer.movetowardsdroplocation(bookingid);
		response.sendRedirect(googleMapUrl);
	}
	@PatchMapping("/rider/completeride")
	public Responsestructure<String> rideCompleted(@RequestParam int booking) {
		return riderServicelayer.rideComplete(booking);
	}
	@GetMapping("/rider/riderhistory")
	public List<RiderHistoryDTO> riderhistory(@RequestParam int riderid , @RequestParam String status) {
		return riderServicelayer.riderHistory(riderid,status);
	}
}
