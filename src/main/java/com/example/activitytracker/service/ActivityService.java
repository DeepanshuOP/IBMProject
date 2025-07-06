package com.example.activitytracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitytracker.model.Activity;
import com.example.activitytracker.repository.ActivityRepository;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id).map(activity -> {
            activity.setName(updatedActivity.getName());
            activity.setDescription(updatedActivity.getDescription());
            activity.setStartTime(updatedActivity.getStartTime());
            activity.setEndTime(updatedActivity.getEndTime());
            activity.setCompleted(updatedActivity.isCompleted());
            return activityRepository.save(activity);
        }).orElse(null);
    }

    public boolean deleteActivity(Long id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Activity markAsCompleted(Long id) {
        return activityRepository.findById(id).map(activity -> {
            activity.setCompleted(true);
            return activityRepository.save(activity);
        }).orElse(null);
    }
}
