package com.flipr.placement.controller;

import com.flipr.placement.model.ContactForm;
import com.flipr.placement.model.Newsletter;
import com.flipr.placement.service.ClientService;
import com.flipr.placement.service.ContactFormService;
import com.flipr.placement.service.NewsletterService;
import com.flipr.placement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContactFormService contactFormService;

    @Autowired
    private NewsletterService newsletterService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("clients", clientService.getAllClients());
        return "index";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @PostMapping("/submit-contact")
    public String submitContact(
            @RequestParam String fullName,
            @RequestParam String emailAddress,
            @RequestParam String mobileNumber,
            @RequestParam String city) {
        ContactForm contactForm = new ContactForm();
        contactForm.setFullName(fullName);
        contactForm.setEmailAddress(emailAddress);
        contactForm.setMobileNumber(mobileNumber);
        contactForm.setCity(city);
        contactFormService.saveContactForm(contactForm);
        return "redirect:/?success=true";
    }

    @PostMapping("/subscribe-newsletter")
    public String subscribeNewsletter(@RequestParam String email) {
        Newsletter newsletter = new Newsletter();
        newsletter.setEmailAddress(email);
        newsletterService.subscribeNewsletter(newsletter);
        return "redirect:/?newsletter=success";
    }
}
