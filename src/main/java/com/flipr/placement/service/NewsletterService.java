package com.flipr.placement.service;

import com.flipr.placement.model.Newsletter;
import com.flipr.placement.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsletterService {
    @Autowired
    private NewsletterRepository newsletterRepository;

    public List<Newsletter> getAllSubscriptions() {
        return newsletterRepository.findAll();
    }

    public Newsletter subscribeNewsletter(Newsletter newsletter) {
        if (!newsletterRepository.existsByEmailAddress(newsletter.getEmailAddress())) {
            return newsletterRepository.save(newsletter);
        }
        return null;
    }

    public void deleteSubscription(Long id) {
        newsletterRepository.deleteById(id);
    }
}
