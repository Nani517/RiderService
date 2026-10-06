package com.Rapido.RiderService.Execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import com.Rapido.RiderService.DTO.Responsestructure;

@RestControllerAdvice
public class GlobalExecptionhandler {
	@ExceptionHandler(RideNotFoundExecption.class)
	public Responsestructure<String> handlerRidernotfound() {
		Responsestructure<String> rs = new Responsestructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Rider Not Found");
		rs.setData("Rider Not Found");
		return rs;
	}
	@ExceptionHandler(RidealreadyExistExecption.class)
	public Responsestructure<String> handlealeadyexistrider() {
		Responsestructure<String> responsestructure = new Responsestructure<String>();
		responsestructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
		responsestructure.setMessage("Rider is already exist please login");
		responsestructure.setData("rider is exist");
		return responsestructure;
	}
	@ExceptionHandler(RestClientException.class)
	public Responsestructure<String> handlerestclientexcetion(RestClientException e){
		// IMPORTANT: print actual error
		System.out.println("========== REST TEMPLATE ERROR ==========");

		System.out.println("Exception Type = " + e.getClass().getName());

		System.out.println("Exception Message = " + e.getMessage());

		e.printStackTrace();

		System.out.println("==========================================");

		Responsestructure<String> error = new Responsestructure<>();

		error.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		error.setMessage("Error calling CustomerService: " + e.getMessage());

		error.setData(null);

		return error;
	}
}
