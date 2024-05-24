package com.example.todobackend.Service;

import com.example.todobackend.Entity.Appointment;
import com.example.todobackend.Entity.Category;
import com.example.todobackend.Repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;


    public Appointment addAppointment(Appointment appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        if (appointmentOptional.isPresent()) {
            throw new IllegalArgumentException("Appointment already exists");
        }
        return appointmentRepository.save(appointment);
    }

    public Appointment editAppointment(Appointment appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        if (appointmentOptional.isPresent()) {
            return appointmentRepository.save(appointment);
        }
        throw new IllegalArgumentException("Appointment does not exit");
    }

    public Appointment getAppointment(long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            return appointmentOptional.get();
        }
        throw new IllegalArgumentException("Appointment does not exit");
    }

    public void deleteAppointment(long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            appointmentRepository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Appointment does not exit");
    }

    public List<Appointment> getAllAppointments() {
        return  appointmentRepository.findAll();
    }
}
