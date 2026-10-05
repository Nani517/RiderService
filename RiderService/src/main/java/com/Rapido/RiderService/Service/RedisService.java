package com.Rapido.RiderService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.stereotype.Service;
import com.Rapido.RiderService.DTO.Cordinate;
import com.Rapido.RiderService.Execption.RideNotFoundExecption;
import com.Rapido.RiderService.Repository.RiderRepository;
import com.Rapido.RiderService.entity.Rider;

@Service
public class RedisService {
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private RiderRepository riderRepository;

	public void saveRiderLocation(int riderId, String vehicleType, Cordinate coordinate) {
		// making folder according to vehicle type
		String key = "vehicle:" + vehicleType + ":locations";

		// give the rider to findout
		String member = "rider:" + riderId;

		// Storing the location in redis
		redisTemplate.opsForGeo().add(key, new Point(coordinate.getLongtitude(), coordinate.getLatitude()), member);
		System.out.println("complete");
	}

	public List<String> findNearbyCustomers(int riderId, String vehicleType) {

		String riderKey = "vehicle:" + vehicleType + ":locations";

		String riderMember = "rider:" + riderId;

		List<Point> riderLocations = redisTemplate.opsForGeo().position(riderKey, riderMember);

		if (riderLocations == null || riderLocations.isEmpty()) {

			System.out.println("RIDER NOT FOUND: " + riderMember);

			return List.of();
		}

		Point riderLocation = riderLocations.get(0);

		System.out.println("Rider location: " + riderLocation);

		// Check customer GEO key
		Long customerCount = redisTemplate.opsForZSet().zCard("customer:locations");

		System.out.println("Customer GEO count: " + customerCount);

		if (customerCount == null || customerCount == 0) {

			System.out.println("customer:locations is EMPTY");

			return List.of();
		}

		BoundGeoOperations<String, String> customerGeo = redisTemplate.boundGeoOps("customer:locations");

		Distance radius = new Distance(5, Metrics.KILOMETERS);

		GeoResults<RedisGeoCommands.GeoLocation<String>> results = customerGeo.search(
				GeoReference.fromCoordinate(riderLocation), radius,
				RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().includeDistance().sortAscending().limit(10));

		List<String> nearbyCustomers = new ArrayList<>();

		for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {

			String customerId = result.getContent().getName();

			nearbyCustomers.add(customerId);
		}

		System.out.println("Nearby customers: " + nearbyCustomers);

		return nearbyCustomers;
	}

}
