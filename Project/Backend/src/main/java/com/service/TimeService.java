package com.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.model.AssignedClass;
import com.repository.AssignedClassRepository;

import java.util.Date;
import java.util.List;

@Service
public class TimeService {

    private AssignedClassRepository assignedClassRepository = null;

    // Constructor injection is recommended for repository injection
    public TimeService(AssignedClassRepository assignedClassRepository) {
        this.assignedClassRepository = assignedClassRepository;
    }

    @Scheduled(cron = "0 0 0 * * SUN") // Runs at midnight every Sunday
    public void updateTimers() {
        List<AssignedClass> classes = assignedClassRepository.findAll();
        if(classes.isEmpty())return;
        Date currentDate = new Date();
        classes.forEach(assignedClass -> {
            if (!assignedClass.getStartDate().after(currentDate) && assignedClass.getTimer() < 104) {
                assignedClass.setTimer(assignedClass.getTimer() + 1);
                assignedClassRepository.save(assignedClass);
            }
        });
    }
}

