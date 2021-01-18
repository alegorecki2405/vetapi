package grizzly.vetapi.controller;

import grizzly.vetapi.model.Appointment;
import grizzly.vetapi.repository.AppointmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping
    ResponseEntity<Object> createAppointment(@RequestBody @Valid Appointment appointment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(errors);
        }
        Appointment result = appointmentRepository.save(appointment);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping(params = {"date", "doctor"})
    ResponseEntity<List<Appointment>> readAllAppointmentsForDateAndDoctor(@RequestParam String date, @RequestParam String doctor) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateToFind = LocalDate.parse(date, formatter);
        return ResponseEntity.ok(appointmentRepository.findAllByDateAndAndDoctor(dateToFind, doctor));
    }

    @DeleteMapping(params = {"id", "customerId", "pin"})
    ResponseEntity<Integer> cancelAppointments(@RequestParam int id, @RequestParam int customerId, @RequestParam int pin) {
        int isRemoved = appointmentRepository.deleteAppointmentByIdAndCustomerIdAndPin(id, customerId, pin);
        if (0 == isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id);
    }
}
