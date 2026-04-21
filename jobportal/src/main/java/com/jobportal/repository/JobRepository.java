package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobportal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    // Custom query (required)
    @Query("SELECT j FROM Job j WHERE j.location = :location")
    List<Job> findByLocation(@Param("location") String location);
}