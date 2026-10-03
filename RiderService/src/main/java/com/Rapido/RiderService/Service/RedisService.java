package com.Rapido.RiderService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.Rapido.RiderService.DTO.Cordinate;

@Service
public class RedisService {
	@Autowired
	private StringRedisTemplate redisTemplate;

	public void saveRiderLocation(int riderId, String vehicleType, Cordinate coordinate) {
		// making folder according to vehicle type
		String key = "vehicle:" + vehicleType + ":locations";

		// give the rider to findout
		String member = "rider:" + riderId;

		// Storing the location in redis
		redisTemplate.opsForGeo().add(key, new Point(coordinate.getLongtitude(), coordinate.getLatitude()), member);
		System.out.println("complete");
	}
}
