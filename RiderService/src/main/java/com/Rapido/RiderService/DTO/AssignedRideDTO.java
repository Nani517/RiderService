package com.Rapido.RiderService.DTO;

public class AssignedRideDTO {

	private int bookingId;
	private long customerId;
	private double distance;
	private double duration;

	public AssignedRideDTO(int bookingId, long customerId, double distance, double duration) {
		super();
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.distance = distance;
		this.duration = duration;
		
	}

	public AssignedRideDTO() {
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

//	public String getOtp() {
//		return otp;
//	}
//
//	public void setOtp(String otp) {
//		this.otp = otp;
//	}
//
//	public String getVehicle() {
//		return vehicle;
//	}
//
//	public void setVehicle(String vehicle) {
//		this.vehicle = vehicle;
//	}
}
