package com.Rapido.RiderService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisAccessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Rapido.RiderService.DTO.Cordinate;
import com.Rapido.RiderService.DTO.CreateRiderAccount;
import com.Rapido.RiderService.DTO.Responsestructure;
import com.Rapido.RiderService.DTO.VehicleDTO;
import com.Rapido.RiderService.Execption.RideNotFoundExecption;
import com.Rapido.RiderService.Execption.RidealreadyExistExecption;
import com.Rapido.RiderService.Repository.RiderRepository;
import com.Rapido.RiderService.Repository.VehicleRepository;
import com.Rapido.RiderService.entity.Rider;
import com.Rapido.RiderService.entity.Vehicle;

@Service
public class RiderServicelayer {
	@Autowired
	private RiderRepository riderRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

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

	@Autowired
	private RedisService redisService;

	public void SendCordinateLocation(int riderid, String vehicletype, Cordinate cordinate) {
		// TODO Auto-generated method stub
		redisService.saveRiderLocation(riderid, vehicletype, cordinate);
	}

}
