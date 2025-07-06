package com.example.activitytracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.activitytracker.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
