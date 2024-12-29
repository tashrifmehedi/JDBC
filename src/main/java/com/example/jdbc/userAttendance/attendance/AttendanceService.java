package com.example.jdbc.userAttendance.attendance;

import com.example.jdbc.userAttendance.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance saveAttendance(long id, LocalDate date, String status){
        Attendance attendance = new Attendance();
        attendance.setUser(new User(id));
        attendance.setAttendanceDate(date);
        attendance.setStatus(status);
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceReport(Long id,LocalDate startDate, LocalDate endDate){
        return attendanceRepository.findByUserIdAndAttendanceDateIsBetween(id, startDate, endDate);
    }
}
