package com.Rapido.RiderService.DTO;

public class BookingDTO {
    private int riderId;
    private String pickupLoc;
    private String destinationLoc;
    private double fare;
	public BookingDTO(int riderId, String pickupLoc, String destinationLoc, double fare) {
		super();
		this.riderId = riderId;
		this.pickupLoc = pickupLoc;
		this.destinationLoc = destinationLoc;
		this.fare = fare;
	}
	public BookingDTO() {
		super();
	}
	public int getRiderId() {
		return riderId;
	}
	public void setRiderId(int riderId) {
		this.riderId = riderId;
	}
	public String getPickupLoc() {
		return pickupLoc;
	}
	public void setPickupLoc(String pickupLoc) {
		this.pickupLoc = pickupLoc;
	}
	public String getDestinationLoc() {
		return destinationLoc;
	}
	public void setDestinationLoc(String destinationLoc) {
		this.destinationLoc = destinationLoc;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
    
}
