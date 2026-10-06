package com.Rapido.RiderService.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.Rapido.RiderService.DTO.AssignedRideDTO;
import com.Rapido.RiderService.DTO.BookingDTO;
import com.Rapido.RiderService.DTO.Cordinate;
import com.Rapido.RiderService.DTO.CreateRiderAccount;
import com.Rapido.RiderService.DTO.Responsestructure;
import com.Rapido.RiderService.DTO.RideDetails;
import com.Rapido.RiderService.DTO.VehicleDTO;
import com.Rapido.RiderService.Execption.RideNotFoundExecption;
import com.Rapido.RiderService.Execption.RidealreadyExistExecption;
import com.Rapido.RiderService.Repository.RiderRepository;
import com.Rapido.RiderService.Repository.VehicleRepository;
import com.Rapido.RiderService.entity.Rider;
import com.Rapido.RiderService.entity.Vehicle;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class RiderServicelayer {
	@Autowired
	private RiderRepository riderRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RedisService redisService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Responsestructure<Rider> CreateAccountOfRider(CreateRiderAccount cra) {
		// TODO Auto-generated method stub
		if (riderRepository.existsByMobile(cra.getMoblie()) || riderRepository.existsByEmail(cra.getEmail())
				|| riderRepository.existsByDrivinglicence(cra.getDrivinglicence())) {
			throw new RidealreadyExistExecption();
		}
		Rider rider = new Rider();
		rider.setName(cra.getName());
		rider.setMobile(cra.getMoblie());
		rider.setGender(cra.getGender());
		rider.setEmail(cra.getEmail());
		rider.setDrivinglicence(cra.getDrivinglicence());
		rider.setVehicle(null);
		riderRepository.save(rider);
		Responsestructure<Rider> responsestructure = new Responsestructure<Rider>();
		responsestructure.setStatuscode(HttpStatus.CREATED.value());
		responsestructure.setMessage("Ride Account is created Successfully");
		responsestructure.setData(rider);
		return responsestructure;
	}

	public Responsestructure<Vehicle> UpdateVehicle(int id, VehicleDTO vehicleDTO) {
		// TODO Auto-generated method stub
		Rider rider = riderRepository.findById(id).orElseThrow(() -> new RideNotFoundExecption());
		Vehicle vehicle = new Vehicle();
		vehicle.setType(vehicleDTO.getType());
		vehicle.setVehicleno(vehicleDTO.getVehicleno());
		vehicle.setName(vehicleDTO.getVehiclename());
		vehicle.setModel(vehicleDTO.getModel());
		rider.setVehicle(vehicle);
		vehicleRepository.save(vehicle);
		riderRepository.save(rider);
		Responsestructure<Vehicle> responsestructure = new Responsestructure<Vehicle>();
		responsestructure.setStatuscode(HttpStatus.CREATED.value());
		responsestructure.setMessage("Ride Account is created Successfully");
		responsestructure.setData(vehicle);
		return responsestructure;

	}

	public Responsestructure<Rider> deleteRiderAccount(int id) {
		// TODO Auto-generated method stub
		Rider rider = riderRepository.findById(id).orElseThrow(() -> new RideNotFoundExecption());
		riderRepository.delete(rider);
		vehicleRepository.delete(rider.getVehicle());
		Responsestructure<Rider> responsestructure = new Responsestructure<Rider>();
		responsestructure.setStatuscode(HttpStatus.CREATED.value());
		responsestructure.setMessage("Ride Account is Deleted Successfully");
		responsestructure.setData(rider);
		return responsestructure;
	}

	public Responsestructure<Rider> findById(int id) {
		// TODO Auto-generated method stub
		Rider rider = riderRepository.findById(id).orElseThrow(() -> new RideNotFoundExecption());
		Responsestructure<Rider> responsestructure = new Responsestructure<Rider>();
		responsestructure.setStatuscode(HttpStatus.CREATED.value());
		responsestructure.setMessage("Rider Found");
		responsestructure.setData(rider);
		return responsestructure;
	}

	public Responsestructure<Rider> ChangeTheStatus(int id) {
		// TODO Auto-generated method stub
		Rider rider = riderRepository.findById(id).orElseThrow(() -> new RideNotFoundExecption());
		if (rider.getStatus() == null || rider.getStatus().equalsIgnoreCase("offline")) {
			rider.setStatus("online");
		} else {
			rider.setStatus("offline");
		}
		Responsestructure<Rider> responsestructure = new Responsestructure<Rider>();
		responsestructure.setStatuscode(HttpStatus.UPGRADE_REQUIRED.value());
		responsestructure.setMessage("Rider is update status");
		responsestructure.setData(rider);
		return responsestructure;
	}

	public void SendCordinateLocation(int riderid, String vehicletype, Cordinate cordinate) {
		// TODO Auto-generated method stub
		Rider rider = riderRepository.findById(riderid).orElseThrow(()->new RideNotFoundExecption());
		vehicletype = rider.getVehicle().getType();
		redisService.saveRiderLocation(rider.getId(), vehicletype, cordinate);
	}

	public List<AssignedRideDTO> FindAllAssingedride(int riderid) {
		// TODO Auto-generated method stub
//		Rider rider = riderRepository.findById(riderid).orElseThrow(()->new RideNotFoundExecption());
		return redisService.getAllAssignedRides(riderid);
	}

	public BookingDTO findBookingById(int bookingId) {
		String url = "http://localhost:8081/customer/booking/" + bookingId;
		return restTemplate.getForObject(url, BookingDTO.class);
	}

	public Responsestructure<String> acceptingBooking(int bookingId, int riderId) {
		// 1. Check whether ride is assigned to rider
		boolean assigned = redisService.isRideAssigned(riderId, bookingId);
		System.out.println("Ride assigned = " + assigned);
		if (!assigned) {
			Responsestructure<String> response = new Responsestructure<>();
			response.setStatuscode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Booking " + bookingId + " is not assigned to rider " + riderId);
			response.setData(null);
			return response;
		}
		// 2. CustomerService URL
		String url = "http://localhost:8081/customer/booking/" + bookingId + "/assignRider/" + riderId;
		System.out.println("CustomerService URL = " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Void> request = new HttpEntity<>(headers);
		
			// 3. Call CustomerService
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
			System.out.println("CustomerService HTTP Status = " + response.getStatusCode());
			System.out.println("CustomerService Response = " + response.getBody());
			// 4. Make sure CustomerService responded
			if (response.getBody() == null) {
				Responsestructure<String> error = new Responsestructure<>();
				error.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				error.setMessage("Empty response from CustomerService");
				error.setData(null);
				return error;
			}
//			Rider rider = riderRepository.findById(riderId).orElseThrow(()-> new RideNotFoundExecption());
//			rider.setStatus("Busy");
			// 5. Update Redis after successful DB update
			redisService.acceptRide(riderId, bookingId);
			// 6. Remove booking from pending requests
			redisService.removeAssignedRide(riderId, bookingId);
			// 7. Return success
			Responsestructure<String> result = new Responsestructure<>();
			result.setStatuscode(HttpStatus.OK.value());
			result.setMessage("Booking accepted successfully");
			result.setData("Booking " + bookingId + " accepted by rider " + riderId);
			return result;
		
	}

	public void ridermovingtopicpuplocation(int bookingid) {
		// TODO Auto-generated method stub

	}

}
