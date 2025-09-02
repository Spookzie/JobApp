package com.spookzie.jobapp.job.impl;

import com.spookzie.jobapp.job.Job;
import com.spookzie.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class JobServiceImpl implements JobService
{
    private final List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;


    /*  GET     */
    @Override
    public List<Job> findAll()
    {
        return this.jobs;
    }

    @Override
    public Job findById(Long id)
    {
        for(Job job : jobs)
        {
            if(job.getId().equals(id))
                return job;
        }

        return null;
    }


    /*  POST    */
    @Override
    public void createJob(Job job)
    {
        job.setId(this.nextId++);
        this.jobs.add(job);
    }


    /*  DELETE  */
    @Override
    public boolean deleteById(Long id)
    {
        return this.jobs.remove(
                this.findById(id)
        );
    }


    /*  UPDATE  */
    @Override
    public Job updateJob(Long id, Job job)
    {
        Job existingJob = this.findById(id);

        if(existingJob != null)
        {
            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setMinSalary(job.getMinSalary());
            existingJob.setMaxSalary(job.getMaxSalary());
            existingJob.setLocation(job.getLocation());
        }

        return existingJob;
    }
}