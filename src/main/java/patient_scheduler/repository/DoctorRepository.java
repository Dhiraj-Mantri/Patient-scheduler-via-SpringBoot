package patient_scheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import patient_scheduler.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialtyContainingIgnoreCase(String specialty);
}
