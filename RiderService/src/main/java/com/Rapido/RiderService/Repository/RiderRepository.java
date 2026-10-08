package com.Rapido.RiderService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Rapido.RiderService.entity.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer>{
	public boolean existsByMobile(long moblie);


	public boolean existsByDrivinglicence(String drivinglicence);


	public boolean existsByEmail(String email);
	

}