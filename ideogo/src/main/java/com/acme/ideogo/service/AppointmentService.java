package com.acme.ideogo.service;

import com.acme.ideogo.model.Appointment;
import com.acme.ideogo.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    Page<Skill> getAllSkillsByProjectScheduleId(Long projectScheduleId, Pageable pageable);
    Skill createAppointment(Long projectScheduleId, Appointment appointment);
    Skill updateAppointment(Long projectScheduleId, Long appointmentId, Appointment appointmentDetails);
    ResponseEntity<?> deleteAppointment(Long projectScheduleId, Long appointmentId);
}
