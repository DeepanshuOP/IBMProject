package com.example.activitytracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitytracker.model.Activity;
import com.example.activitytracker.service.ActivityService;

@RestController
@RequestMapping("/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/{id}")
    public Optional<Activity> getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return activityService.updateActivity(id, activity);
    }

    @DeleteMapping("/{id}")
    public String deleteActivity(@PathVariable Long id) {
        boolean deleted = activityService.deleteActivity(id);
        return deleted ? "Activity deleted." : "Activity not found.";
    }

    @PutMapping("/{id}/done")
    public Activity markActivityAsDone(@PathVariable Long id) {
        return activityService.markAsCompleted(id);
    }
}
