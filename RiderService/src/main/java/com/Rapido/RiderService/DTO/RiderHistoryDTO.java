package com.Rapido.RiderService.DTO;


public class RiderHistoryDTO {
	private int bookinid;
	private String destinationLoc;
	private String pickupLoc;
	private Cordinate desCordinate;
	private Cordinate sourceCordinate;
	private String paymentType;
	private String vechicType;
	private String bookingdate;
	private String bookingtime;
	private String dropTime;
	private double fare;
	private String status;
	private Double ridershare;
	private Double platformshare;
	public RiderHistoryDTO(int bookinid, String destinationLoc, String pickupLoc, Cordinate desCordinate,
			Cordinate sourceCordinate, String paymentType, String vechicType, String bookingdate, String bookingtime,
			String dropTime, double fare, String status, Double ridershare, Double platformshare) {
		super();
		this.bookinid = bookinid;
		this.destinationLoc = destinationLoc;
		this.pickupLoc = pickupLoc;
		this.desCordinate = desCordinate;
		this.sourceCordinate = sourceCordinate;
		this.paymentType = paymentType;
		this.vechicType = vechicType;
		this.bookingdate = bookingdate;
		this.bookingtime = bookingtime;
		this.dropTime = dropTime;
		this.fare = fare;
		this.status = status;
		this.ridershare = ridershare;
		this.platformshare = platformshare;
	}
	public RiderHistoryDTO() {
		super();
	}
	public int getBookinid() {
		return bookinid;
	}
	public void setBookinid(int bookinid) {
		this.bookinid = bookinid;
	}
	public String getDestinationLoc() {
		return destinationLoc;
	}
	public void setDestinationLoc(String destinationLoc) {
		this.destinationLoc = destinationLoc;
	}
	public String getPickupLoc() {
		return pickupLoc;
	}
	public void setPickupLoc(String pickupLoc) {
		this.pickupLoc = pickupLoc;
	}
	public Cordinate getDesCordinate() {
		return desCordinate;
	}
	public void setDesCordinate(Cordinate desCordinate) {
		this.desCordinate = desCordinate;
	}
	public Cordinate getSourceCordinate() {
		return sourceCordinate;
	}
	public void setSourceCordinate(Cordinate sourceCordinate) {
		this.sourceCordinate = sourceCordinate;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getVechicType() {
		return vechicType;
	}
	public void setVechicType(String vechicType) {
		this.vechicType = vechicType;
	}
	public String getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(String bookingdate) {
		this.bookingdate = bookingdate;
	}
	public String getBookingtime() {
		return bookingtime;
	}
	public void setBookingtime(String bookingtime) {
		this.bookingtime = bookingtime;
	}
	public String getDropTime() {
		return dropTime;
	}
	public void setDropTime(String dropTime) {
		this.dropTime = dropTime;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getRidershare() {
		return ridershare;
	}
	public void setRidershare(Double ridershare) {
		this.ridershare = ridershare;
	}
	public Double getPlatformshare() {
		return platformshare;
	}
	public void setPlatformshare(Double platformshare) {
		this.platformshare = platformshare;
	}
	
}
