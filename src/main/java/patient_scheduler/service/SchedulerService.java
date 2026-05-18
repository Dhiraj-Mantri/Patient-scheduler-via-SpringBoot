package patient_scheduler.service;

import java.util.List;

import org.springframework.stereotype.Service;

import patient_scheduler.model.Appointment;
import patient_scheduler.model.Doctor;
import patient_scheduler.repository.AppointmentRepository;
import patient_scheduler.repository.DoctorRepository;

@Service
public class SchedulerService {

    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;

    public SchedulerService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        seedDoctors();
    }

    private void seedDoctors() {
        if (doctorRepository.count() > 0) {
            return;
        }

        doctorRepository.save(new Doctor("Dr. Smith", "Cardiology"));
        doctorRepository.save(new Doctor("Dr. Kumar", "Dermatology"));
        doctorRepository.save(new Doctor("Dr. Anna", "Neurology"));
        doctorRepository.save(new Doctor("Dr. Maria", "Pediatrics"));
        doctorRepository.save(new Doctor("Dr. Lee", "Orthopedics"));
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> searchDoctors(String specialty) {
        if (specialty == null || specialty.isBlank()) {
            return getDoctors();
        }

        return doctorRepository.findBySpecialtyContainingIgnoreCase(specialty);
    }

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAllByOrderByDateAscTimeAscIdAsc();
    }

    public List<Appointment> getAppointmentsForDoctor(String doctorName) {
        return appointmentRepository.findByDoctorNameOrderByDateAscTimeAscIdAsc(doctorName);
    }

    public boolean hasConflict(String doctorName, String date, String time) {
        return appointmentRepository.existsByDoctorNameAndDateAndTimeAndStatus(doctorName, date, time, "Booked");
    }

    public boolean bookAppointment(String patientName, String doctorName, String date, String time) {
        if (hasConflict(doctorName, date, time)) {
            return false;
        }

        appointmentRepository.save(new Appointment(patientName, doctorName, date, time));
        return true;
    }

    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);

        if (appointment != null) {
            appointment.cancel();
            appointmentRepository.save(appointment);
        }
    }

    public long countBookedAppointments() {
        return appointmentRepository.countByStatus("Booked");
    }

    public long countCancelledAppointments() {
        return appointmentRepository.countByStatus("Cancelled");
    }
}
