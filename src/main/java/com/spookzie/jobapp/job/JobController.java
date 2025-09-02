package com.spookzie.jobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/jobs")
@RestController
public class JobController
{
    private final JobService jobService;

    public JobController(JobService jobService)
    {
        this.jobService = jobService;
    }


    /*  GET     */
    @GetMapping()
    public ResponseEntity<List<Job>> findAll()
    {
        return new ResponseEntity<>(
                this.jobService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id)
    {
        Job jobFound =  this.jobService.findById(id);

        if(jobFound == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jobFound, HttpStatus.OK);
    }


    /*  POST    */
    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody Job job)
    {
        this.jobService.createJob(job);

        return new ResponseEntity<>(
                "Job created successfully",
                HttpStatus.CREATED
        );
    }


    /*  DELETE  */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id)
    {
        boolean deleted = this.jobService.deleteById(id);

        if(deleted)
            return new ResponseEntity<>("Job was successfully deleted", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /*  UPDATE  */
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job)
    {
        Job updatedJob = this.jobService.updateJob(id, job);

        if(updatedJob != null)
            return new ResponseEntity<>(updatedJob, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}