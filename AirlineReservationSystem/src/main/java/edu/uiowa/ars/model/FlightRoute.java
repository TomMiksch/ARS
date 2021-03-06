package edu.uiowa.ars.model;

import java.util.List;
import java.util.function.UnaryOperator;
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

import org.joda.time.LocalDate;

@Entity
@Table(name = "FLIGHT_ROUTE")
public final class FlightRoute {

    public enum Airports {
        CID("CID"),
        ORD("ORD"),
        ATL("ATL"),
        SFO("SFO"),
        LCY("LCY");

        private final String identifier;

        Airports(final String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return this.identifier;
        }

        public static List<String> getAllIdentifiers() {
            return Stream.of(Airports.values()).map(Airports::getIdentifier).collect(Collectors.toList());
        }
    }

    public enum Frequency {
        DAILY("Daily", date -> date.plusDays(1)),
        WEEKLY("Weekly", date -> date.plusWeeks(1)),
        BIWEEKLY("Biweekly", date -> date.plusWeeks(2)),
        MONTHLY("Monthly", date -> date.plusMonths(1));

        private final String identifier;
        private final UnaryOperator<LocalDate> dateFunction;

        Frequency(final String identifier, final UnaryOperator<LocalDate> dateFunction) {
            this.identifier = identifier;
            this.dateFunction = dateFunction;
        }

        public String getIdentifier() {
            return this.identifier;
        }

        public static List<String> getAllIdentifiers() {
            return Stream.of(Frequency.values()).map(Frequency::getIdentifier).collect(Collectors.toList());
        }

        public LocalDate apply(final LocalDate orig) {
            return dateFunction.apply(orig);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 10)
    @Column(name = "SYMBOL", nullable = false)
    private String symbol;

    @Size(min = 1, max = 10)
    @Column(name = "AIRCRAFT", nullable = false)
    private String aircraft;

    @Min(value = 0)
    @Column(name = "FIRST_CLASS_PRICE", nullable = false)
    private int firstClassPrice;

    @Min(value = 0)
    @Column(name = "BUSINESS_CLASS_PRICE", nullable = false)
    private int businessClassPrice;

    @Min(value = 0)
    @Column(name = "ECONOMY_CLASS_PRICE", nullable = false)
    private int economyClassPrice;

    @Size(min = 1, max = 50)
    @Column(name = "ORIGIN", nullable = false)
    private String origin;

    @Size(min = 1, max = 50)
    @Column(name = "DESTINATION", nullable = false)
    private String destination;

    @Size(min = 1, max = 20)
    @Column(name = "START_TIME", nullable = false)
    private String startTime;

    @Size(min = 1, max = 20)
    @Column(name = "END_TIME", nullable = false)
    private String endTime;

    @Size(min = 1, max = 10)
    @Column(name = "FREQUENCY", nullable = false)
    private String frequency;

    @Column(name = "BEGIN_DATE", nullable = false)
    private String beginDate;

    @Column(name = "END_DATE", nullable = false)
    private String endDate;

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

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(final String aircraft) {
        this.aircraft = aircraft;
    }

    public int getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(final int firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public int getBusinessClassPrice() {
        return businessClassPrice;
    }

    public void setBusinessClassPrice(final int businessClassPrice) {
        this.businessClassPrice = businessClassPrice;
    }

    public int getEconomyClassPrice() {
        return economyClassPrice;
    }

    public void setEconomyClassPrice(final int economyClassPrice) {
        this.economyClassPrice = economyClassPrice;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(final String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(final String frequency) {
        this.frequency = frequency;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(final String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }
}
