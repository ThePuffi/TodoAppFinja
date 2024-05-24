package com.example.appointmentbackend.Controller;


import com.example.todobackend.Entity.Appointment;
import com.example.todobackend.Service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/addAppointment")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(this.appointmentService.addAppointment(appointment), HttpStatus.CREATED);
    }
    @GetMapping("/getAllAppointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return new ResponseEntity<>(this.appointmentService.getAllAppointments(), HttpStatus.FOUND);
    }
    @GetMapping("/findAppointmentById")
    public ResponseEntity<Appointment> findAppointmentById(@RequestParam("AppointmentId") Long appointmentId) {
        return new ResponseEntity<>(this.appointmentService.getAppointment(appointmentId), HttpStatus.FOUND);
    }
    @PutMapping("/updateAppointment")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(this.appointmentService.editAppointment(appointment), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAppointment")
    public ResponseEntity<HttpStatus> deleteAppointment(@RequestParam("AppointmentId") long appointmentId) {
        this.appointmentService.deleteAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
