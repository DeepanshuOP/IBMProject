package com.example.activitytracker.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.activitytracker.model.Activity;
import com.example.activitytracker.repository.ActivityRepository;

public class ActivityServiceTests {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

    private Activity sampleActivity;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleActivity = new Activity(
            "Test Activity",
            "This is a test",
            LocalDateTime.now(),
            LocalDateTime.now().plusHours(1),
            false
        );
    }

    @Test
    void testCreateActivity() {
        when(activityRepository.save(sampleActivity)).thenReturn(sampleActivity);

        Activity created = activityService.createActivity(sampleActivity);
        assertNotNull(created);
        assertEquals("Test Activity", created.getName());
    }

    @Test
    void testGetAllActivities() {
        when(activityRepository.findAll()).thenReturn(Arrays.asList(sampleActivity));

        assertEquals(1, activityService.getAllActivities().size());
    }

    @Test
    void testGetActivityById() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(sampleActivity));

        Optional<Activity> result = activityService.getActivityById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void testDeleteActivity() {
        when(activityRepository.existsById(1L)).thenReturn(true);
        doNothing().when(activityRepository).deleteById(1L);

        boolean result = activityService.deleteActivity(1L);
        assertTrue(result);
    }

    @Test
    void testMarkAsCompleted() {
        sampleActivity.setCompleted(false);
        when(activityRepository.findById(1L)).thenReturn(Optional.of(sampleActivity));
        when(activityRepository.save(any(Activity.class))).thenReturn(sampleActivity);

        Activity result = activityService.markAsCompleted(1L);
        assertTrue(result.isCompleted());
    }
}
