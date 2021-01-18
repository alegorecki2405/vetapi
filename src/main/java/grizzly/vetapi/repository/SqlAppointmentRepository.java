package grizzly.vetapi.repository;

import grizzly.vetapi.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlAppointmentRepository extends AppointmentRepository, JpaRepository<Appointment, Integer> {
}
