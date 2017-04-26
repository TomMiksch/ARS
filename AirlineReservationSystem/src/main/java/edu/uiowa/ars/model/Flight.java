package edu.uiowa.ars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "FLIGHT")
public final class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "FLIGHT_ROUTE_ID", nullable = false)
	private String flightRouteId;

	@Size(min = 10, max = 10)
	@Column(name = "DATE", nullable = false)
	private String date;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getFlightRouteId() {
		return flightRouteId;
	}

	public void setFlightRouteId(final String flightRouteId) {
		this.flightRouteId = flightRouteId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

}
