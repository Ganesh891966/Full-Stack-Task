package org.example.membership.controller;

import org.example.membership.entity.Plan;
import org.example.membership.repository.PlanRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanRepository planRepository;

    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @PostMapping
    public Plan createPlan(@RequestBody Plan plan) {
        return planRepository.save(plan);
    }

    @GetMapping
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }
    @PutMapping("/{id}")
    public Plan updatePlan(@PathVariable Long id, @RequestBody Plan updatedPlan) {

        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        plan.setName(updatedPlan.getName());
        plan.setPrice(updatedPlan.getPrice());
        plan.setDurationInDays(updatedPlan.getDurationInDays());

        return planRepository.save(plan);
    }
}