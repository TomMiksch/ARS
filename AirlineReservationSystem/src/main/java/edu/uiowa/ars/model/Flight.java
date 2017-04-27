package edu.uiowa.ars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "FLIGHT")
public final class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "FLIGHT_ROUTE_ID", nullable = false)
	private int flightRouteId;

	@Size(min = 10, max = 10)
	@Column(name = "DATE", nullable = false)
	private String date;

	@Min(value = 0)
	@Column(name = "FIRST_CLASS_SEATS", nullable = false)
	private int firstClassSeats;

	@Min(value = 0)
	@Column(name = "BUSINESS_CLASS_SEATS", nullable = false)
	private int businessClassSeats;

	@Min(value = 0)
	@Column(name = "ECONOMY_CLASS_SEATS", nullable = false)
	private int economyClassSeats;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getFlightRouteId() {
		return flightRouteId;
	}

	public void setFlightRouteId(final int flightRouteId) {
		this.flightRouteId = flightRouteId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public int getFirstClassSeats() {
		return firstClassSeats;
	}

	public void setFirstClassSeats(final int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}

	public int getBusinessClassSeats() {
		return businessClassSeats;
	}

	public void setBusinessClassSeats(final int businessClassSeats) {
		this.businessClassSeats = businessClassSeats;
	}

	public int getEconomyClassSeats() {
		return economyClassSeats;
	}

	public void setEconomyClassSeats(final int economyClassSeats) {
		this.economyClassSeats = economyClassSeats;
	}

}
