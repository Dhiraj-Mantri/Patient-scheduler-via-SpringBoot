package patient_scheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import patient_scheduler.service.SchedulerService;

@Controller
public class AdminController {

    private SchedulerService schedulerService;

    public AdminController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("doctorCount", schedulerService.getDoctors().size());
        model.addAttribute("totalAppointments", schedulerService.getAppointments().size());
        model.addAttribute("bookedAppointments", schedulerService.countBookedAppointments());
        model.addAttribute("cancelledAppointments", schedulerService.countCancelledAppointments());
        model.addAttribute("appointments", schedulerService.getAppointments());
        return "admin";
    }
}
