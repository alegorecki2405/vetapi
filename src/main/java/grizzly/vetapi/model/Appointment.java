package grizzly.vetapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 1000, message = "custmer's id must be 4 digits and must be specified")
    @Max(value = 9999, message = "custmer's id must be 4 digits and must be specified")
    private int customerId;

    @Min(value = 1000, message = "custmer's pin must be 4 digits and must be specified")
    @Max(value = 9999, message = "custmer's pin must be 4 digits and must be specified")
    private int pin;

    @NotNull(message = "time of the appointment must be specified and should be in hh:mm format")
    private LocalTime time;

    @NotNull(message = "date of the visit must be specified and should be in yyyy-MM-dd format")
    @Future(message = "date must be in future")
    private LocalDate date;

    @NotBlank(message = "doctor must be specified")
    private String doctor;

    public Appointment() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    private void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDoctor() {
        return doctor;
    }

    private void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    private int getPin() {
        return pin;
    }

    private void setPin(int pin) {
        this.pin = pin;
    }

    private int getCustomerId() {
        return customerId;
    }

    private void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
