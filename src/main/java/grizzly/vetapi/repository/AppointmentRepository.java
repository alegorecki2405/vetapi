package grizzly.vetapi.repository;

import grizzly.vetapi.model.Appointment;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    List<Appointment> findAllByDateAndAndDoctor(LocalDate date, String doctor);

    @Transactional
    int deleteAppointmentByIdAndCustomerIdAndPin(int id, int customerId, int pin);
}
