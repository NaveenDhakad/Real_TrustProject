package com.flipr.placement.service;

import com.flipr.placement.model.ContactForm;
import com.flipr.placement.repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContactFormService {
    @Autowired
    private ContactFormRepository contactFormRepository;

    public List<ContactForm> getAllContactForms() {
        return contactFormRepository.findAll();
    }

    public Optional<ContactForm> getContactFormById(Long id) {
        return contactFormRepository.findById(id);
    }

    public ContactForm saveContactForm(ContactForm contactForm) {
        return contactFormRepository.save(contactForm);
    }

    public void deleteContactForm(Long id) {
        contactFormRepository.deleteById(id);
    }
}
