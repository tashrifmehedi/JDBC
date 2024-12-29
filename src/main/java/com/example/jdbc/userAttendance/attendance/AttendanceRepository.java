package com.example.jdbc.userAttendance.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    List<Attendance> findByUserIdAndAttendanceDateIsBetween(Long userId, LocalDate startDate, LocalDate endDate);
}
