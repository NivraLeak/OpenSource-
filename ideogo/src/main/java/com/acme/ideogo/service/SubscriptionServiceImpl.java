package com.acme.ideogo.service;

import com.acme.ideogo.model.Membership;
import com.acme.ideogo.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public Page<Membership> getAllSkillsByMembershipId(Long membershipId, Pageable pageable) {
        return null;
    }

    @Override
    public Membership createSubscription(Long membershipId, Subscription subscription) {
        return null;
    }

    @Override
    public Membership updateSubscription(Long membershipId, Long subscriptionId, Subscription subscriptionDetails) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSubscription(Long membershipId, Long subscriptionId) {
        return null;
    }
}
