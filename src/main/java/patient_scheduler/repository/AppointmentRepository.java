package patient_scheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import patient_scheduler.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByOrderByDateAscTimeAscIdAsc();

    List<Appointment> findByDoctorNameOrderByDateAscTimeAscIdAsc(String doctorName);

    boolean existsByDoctorNameAndDateAndTimeAndStatus(String doctorName, String date, String time, String status);

    long countByStatus(String status);
}
