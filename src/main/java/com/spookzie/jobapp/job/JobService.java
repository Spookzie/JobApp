package com.spookzie.jobapp.job;

import java.util.List;


public interface JobService
{
    List<Job> findAll();
    Job findById(Long id);

    void createJob(Job job);

    boolean deleteById(Long id);

    Job updateJob(Long id, Job job);
}