package com.jobportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.Job;
import com.jobportal.repository.JobRepository;
import com.jobportal.service.JobService;

import jakarta.transaction.Transactional; // 🔥 IMPORTANT

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    @Transactional   // 🔥 REQUIRED (assignment)
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}