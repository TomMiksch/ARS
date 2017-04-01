package edu.uiowa.ars.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "AIRCRAFT")
public final class Aircraft {

	public enum AircraftTypes {
		AIRBUS_A330("Airbus A330"),
		AIRBUS_A350("Airbus A350"),
		AIRBUS_A380("Airbus A380"),
		BOEING_737("Boeing 737"),
		BOEING_747("Boeing 747"),
		BOEING_757("Boeing 757"),
		BOEING_767("Boeing 767"),
		BOEING_777("Boeing 777"),
		BOEING_787("Boeing 787");

		private final String identifier;

		AircraftTypes(final String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return this.identifier;
		}

		public static List<String> getAllIdentifiers() {
			return Stream.of(AircraftTypes.values()).map(AircraftTypes::getIdentifier).collect(Collectors.toList());
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 1, max = 10)
	@Column(name = "SYMBOL", nullable = false)
	private String symbol;

	@Column(name = "AIRCRAFT_TYPE", nullable = false)
	private String aircraftType;

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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(final String symbol) {
		this.symbol = symbol;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(final String aircraftType) {
		this.aircraftType = aircraftType;
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
