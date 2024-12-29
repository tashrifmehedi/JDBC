package com.example.jdbc.userAttendance.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping(value = "/saveAttendance")
    public String showMarkAttendancePage() {
        return "userAttendance/attendance";
    }

    @PostMapping(value ="/saveAttendance")
    public String makeAttendance(@RequestParam Long userId, @RequestParam String status){
        attendanceService.saveAttendance(userId, LocalDate.now(),status);
        return "redirect:/";
    }

    @GetMapping(value = "/report")
    public String showReportPage() {
        return "userAttendance/report";
    }
    @PostMapping(value = "/report")
    public String printReport(Model model, @RequestParam Long userId, @RequestParam String start, @RequestParam String end) {
        List<Attendance> report = attendanceService.getAttendanceReport(
                userId,
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
        model.addAttribute("report", report);
        return "userAttendance/report";
    }

}
