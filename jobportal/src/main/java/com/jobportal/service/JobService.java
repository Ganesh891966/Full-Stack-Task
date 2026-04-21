package com.jobportal.service;

import java.util.List;

import com.jobportal.entity.Job;

public interface JobService {
	void deleteJob(Long id);
    List<Job> getAllJobs();
    Job saveJob(Job job);
}