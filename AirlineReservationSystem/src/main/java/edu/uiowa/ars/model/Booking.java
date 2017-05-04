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
@Table(name = "BOOKING")
public final class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 50)
    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "FLIGHT_NUMBER", nullable = false)
    private int flightNumber;

    @Column(name = "CLASS", nullable = false)
    private String seatClass;

    @Min(value = 0)
    @Column(name = "SEATS", nullable = false)
    private int seats;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(final int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(final String seatClass) {
        this.seatClass = seatClass;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(final int seats) {
        this.seats = seats;
    }
}
