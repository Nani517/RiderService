package com.Rapido.RiderService.Service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Rapido.RiderService.DTO.AssignedRideDTO;
import com.Rapido.RiderService.DTO.Cordinate;
import com.Rapido.RiderService.DTO.Responsestructure;
import com.Rapido.RiderService.DTO.RideDetails;
import com.Rapido.RiderService.Execption.RideNotFoundExecption;
import com.Rapido.RiderService.Repository.RiderRepository;
import com.Rapido.RiderService.entity.Rider;

@Service
public class RedisService {
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private RiderRepository riderRepository;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate1;

	public void saveRiderLocation(int riderId, String vehicleType, Cordinate coordinate) {
		// making folder according to vehicle type
		String key = "vehicle:" + vehicleType + ":locations";
		// give the rider to findout
		String member = "rider:" + riderId;
		// Storing the location in redis
		redisTemplate.opsForGeo().add(key, new Point(coordinate.getLongtitude(), coordinate.getLatitude()), member);
		System.out.println("complete");
	}

	public List<AssignedRideDTO> getAllAssignedRides(int riderId) {
		String riderRequestKey = "rider:rider:" + riderId + ":requests";
		// Get all assigned booking IDs
		Map<Object, Object> requests = redisTemplate.opsForHash().entries(riderRequestKey);
		List<AssignedRideDTO> assignedRides = new ArrayList<>();
		if (requests == null || requests.isEmpty()) {
			Responsestructure<AssignedRideDTO> responsestructure = new Responsestructure<AssignedRideDTO>();
			responsestructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responsestructure.setMessage("Rideid not found in the redis");
			responsestructure.setData(null);
			return assignedRides;
		}
		for (Map.Entry<Object, Object> entry : requests.entrySet()) {
			String bookingId = String.valueOf(entry.getKey());
			String rideKey = String.valueOf(entry.getValue());
			// Get complete ride information
			Map<Object, Object> rideData = redisTemplate.opsForHash().entries(rideKey);
			if (rideData == null || rideData.isEmpty()) {
				continue;
			}
			AssignedRideDTO ride = new AssignedRideDTO();
			ride.setBookingId(Integer.parseInt(bookingId));
			ride.setCustomerId(Long.parseLong(String.valueOf(rideData.get("customerId"))));
			ride.setDistance(Double.parseDouble(String.valueOf(rideData.get("distance"))));
			ride.setDuration(Double.parseDouble(String.valueOf(rideData.get("duration"))));
			assignedRides.add(ride);
		}
		return assignedRides;
	}

	public void acceptRide(int riderId, int bookingId) {

		String rideKey = "ride:" + bookingId;

		// Add rider ID
		redisTemplate.opsForHash().put(rideKey, "riderId", String.valueOf(riderId));

		// Add status
		redisTemplate.opsForHash().put(rideKey, "status", "ACCEPTED");

		System.out.println("Ride " + bookingId + " accepted by rider " + riderId);
	}

	public void removeAssignedRide(int riderid, int bookingid) {
		// TODO Auto-generated method stub
		String key = "rider:rider:" + riderid + ":requests";
		System.out.println(key);
		redisTemplate.opsForHash().delete(key, String.valueOf(bookingid));
	}

	public boolean isRideAssigned(int riderId, int bookingId) {

		String rideKey = "ride:" + bookingId;
		String riderKey = "rider:rider:" + riderId + ":requests";

		System.out.println("Ride Key  = " + rideKey);
		System.out.println("Rider Key = " + riderKey);

		// Ride does not exist
		if (!Boolean.TRUE.equals(redisTemplate.hasKey(rideKey))) {
			return false;
		}

		// Rider request list does not exist
		if (!Boolean.TRUE.equals(redisTemplate.hasKey(riderKey))) {
			return false;
		}

		// Booking is not assigned to this rider
		Boolean bookingExists = redisTemplate.opsForHash().hasKey(riderKey, String.valueOf(bookingId));

		return Boolean.TRUE.equals(bookingExists);
	}

//	public boolean verifyOTP(int bookingId, String enteredOtp) {
//
//	    String rideKey = "ride:" + bookingId;
//
//	    String storedOtp =
//	            (String) redisTemplate.opsForHash()
//	                    .get(rideKey, "otp");
//
//	    System.out.println("Ride Key = " + rideKey);
//	    System.out.println("Stored OTP = " + storedOtp);
//	    System.out.println("Entered OTP = " + enteredOtp);
//
//	    if (storedOtp == null) {
//	        return false;
//	    }
//
//	    return storedOtp.equals(enteredOtp);
//	}
	public boolean verifyOTP(int bookingId, String enteredOtp) {

		String rideKey = "ride:" + bookingId;

		String storedOtp = (String) redisTemplate.opsForHash().get(rideKey, "otp");

		System.out.println("Stored OTP = " + storedOtp);
		System.out.println("Entered OTP = " + enteredOtp);

		return storedOtp != null && storedOtp.equals(enteredOtp);
	}
}
