package org.example.membership.controller;

import org.example.membership.entity.*;
import org.example.membership.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    public SubscriptionController(SubscriptionRepository subscriptionRepository,
                                  UserRepository userRepository,
                                  PlanRepository planRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @PostMapping
    public Subscription createSubscription(@RequestParam Long userId,
                                           @RequestParam Long planId) {

        User user = userRepository.findById(userId).orElseThrow();
        Plan plan = planRepository.findById(planId).orElseThrow();

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusDays(plan.getDurationInDays()));
        subscription.setStatus("ACTIVE");

        return subscriptionRepository.save(subscription);
    }

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
    @PutMapping("/{id}/cancel")
    public Subscription cancelSubscription(@PathVariable Long id) {

        Subscription subscription = subscriptionRepository
                .findById(id)
                .orElseThrow();

        subscription.setStatus("CANCELLED");

        return subscriptionRepository.save(subscription);
    }
}