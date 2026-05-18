package patient_scheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import patient_scheduler.service.SchedulerService;

@Controller
public class DoctorController {

    private SchedulerService schedulerService;

    public DoctorController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping("/doctors")
    public String doctors(@RequestParam(required = false) String specialty, Model model) {
        model.addAttribute("doctors", schedulerService.searchDoctors(specialty));
        model.addAttribute("specialty", specialty);
        return "doctors";
    }

    @GetMapping("/doctor-schedule")
    public String doctorSchedule(@RequestParam(required = false) String doctorName, Model model) {
        model.addAttribute("doctors", schedulerService.getDoctors());
        model.addAttribute("selectedDoctor", doctorName);
        model.addAttribute("hasSelectedDoctor", doctorName != null && !doctorName.isBlank());

        if (doctorName != null && !doctorName.isBlank()) {
            model.addAttribute("appointments", schedulerService.getAppointmentsForDoctor(doctorName));
        } else {
            model.addAttribute("appointments", java.util.List.of());
        }

        return "doctor-schedule";
    }
}
