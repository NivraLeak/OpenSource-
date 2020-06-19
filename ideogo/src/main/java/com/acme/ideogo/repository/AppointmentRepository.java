package com.acme.ideogo.repository;

import com.acme.ideogo.model.Appointment;
import com.acme.ideogo.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository< Appointment,Long > {
    Page<Appointment> findByAppointmentId(Long appointmentId, Pageable pageable);
    Optional<Appointment> findByIdAndAppointmentId(Long id, Long appointmentId);
}
