package com.example.jobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if(job != null) {
            return new ResponseEntity<>(jobService.getJobById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Boolean deletedId = jobService.deleteJobById(id);
        if(deletedId) {
            return new ResponseEntity<>("Job with ID " + id + " deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with ID " + id + " not found", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedjob) {
        boolean updated = jobService.updateJobById(id, updatedjob);
        if(updated) {
            return new ResponseEntity<>("Job with ID " + id + " updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with ID " + id + " updated", HttpStatus.NOT_FOUND);

    }
}
