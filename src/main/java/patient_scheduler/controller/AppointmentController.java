package patient_scheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import patient_scheduler.service.SchedulerService;

@Controller
public class AppointmentController {

    private SchedulerService schedulerService;

    public AppointmentController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("appointments", schedulerService.getAppointments());
        return "appointments";
    }

    @GetMapping("/book")
    public String bookForm(@RequestParam(required = false) String doctorName, Model model) {
        model.addAttribute("doctors", schedulerService.getDoctors());
        model.addAttribute("selectedDoctor", doctorName);
        return "book";
    }

    @PostMapping("/book")
    public String bookAppointment(
            @RequestParam String patientName,
            @RequestParam String doctorName,
            @RequestParam String date,
            @RequestParam String time,
            RedirectAttributes redirectAttributes) {

        boolean booked = schedulerService.bookAppointment(patientName, doctorName, date, time);

        if (!booked) {
            redirectAttributes.addAttribute("error", "conflict");
            redirectAttributes.addAttribute("doctorName", doctorName);
            return "redirect:/book";
        }

        return "redirect:/appointments";
    }

    @PostMapping("/cancel")
    public String cancelAppointment(@RequestParam Long id) {
        schedulerService.cancelAppointment(id);
        return "redirect:/appointments";
    }
}
