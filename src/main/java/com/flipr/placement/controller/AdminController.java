package com.flipr.placement.controller;

import com.flipr.placement.model.Project;
import com.flipr.placement.model.Client;
import com.flipr.placement.service.ClientService;
import com.flipr.placement.service.ContactFormService;
import com.flipr.placement.service.NewsletterService;
import com.flipr.placement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContactFormService contactFormService;

    @Autowired
    private NewsletterService newsletterService;

    // Dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("projectCount", projectService.getAllProjects().size());
        model.addAttribute("clientCount", clientService.getAllClients().size());
        model.addAttribute("contactCount", contactFormService.getAllContactForms().size());
        model.addAttribute("newsletterCount", newsletterService.getAllSubscriptions().size());
        return "admin/dashboard";
    }

    // Projects Management
    @GetMapping("/projects")
    public String manageProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "admin/projects";
    }

    @PostMapping("/projects/add")
    public String addProject(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile image) throws IOException {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        if (image != null && !image.isEmpty()) {
            project.setImageData(image.getBytes());
            project.setImageUrl(image.getOriginalFilename());
        }
        projectService.saveProject(project);
        return "redirect:/admin/projects";
    }

    @GetMapping("/projects/edit/{id}")
    public String editProjectForm(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id).orElse(null));
        return "admin/projects";
    }

    @PostMapping("/projects/update/{id}")
    public String updateProject(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile image) throws IOException {
        Project project = projectService.getProjectById(id).orElse(null);
        if (project != null) {
            project.setName(name);
            project.setDescription(description);
            if (image != null && !image.isEmpty()) {
                project.setImageData(image.getBytes());
                project.setImageUrl(image.getOriginalFilename());
            }
            projectService.saveProject(project);
        }
        return "redirect:/admin/projects";
    }

    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/admin/projects";
    }

    // Clients Management
    @GetMapping("/clients")
    public String manageClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "admin/clients";
    }

    @PostMapping("/clients/add")
    public String addClient(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String designation,
            @RequestParam(required = false) MultipartFile image) throws IOException {
        Client client = new Client();
        client.setName(name);
        client.setDescription(description);
        client.setDesignation(designation);
        if (image != null && !image.isEmpty()) {
            client.setImageData(image.getBytes());
            client.setImageUrl(image.getOriginalFilename());
        }
        clientService.saveClient(client);
        return "redirect:/admin/clients";
    }

    @PostMapping("/clients/update/{id}")
    public String updateClient(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String designation,
            @RequestParam(required = false) MultipartFile image) throws IOException {
        Client client = clientService.getClientById(id).orElse(null);
        if (client != null) {
            client.setName(name);
            client.setDescription(description);
            client.setDesignation(designation);
            if (image != null && !image.isEmpty()) {
                client.setImageData(image.getBytes());
                client.setImageUrl(image.getOriginalFilename());
            }
            clientService.saveClient(client);
        }
        return "redirect:/admin/clients";
    }

    @GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/admin/clients";
    }

    // Contact Forms
    @GetMapping("/contacts")
    public String viewContactForms(Model model) {
        model.addAttribute("contacts", contactFormService.getAllContactForms());
        return "admin/contacts";
    }

    @GetMapping("/contacts/delete/{id}")
    public String deleteContactForm(@PathVariable Long id) {
        contactFormService.deleteContactForm(id);
        return "redirect:/admin/contacts";
    }

    // Newsletter
    @GetMapping("/newsletter")
    public String viewNewsletter(Model model) {
        model.addAttribute("subscriptions", newsletterService.getAllSubscriptions());
        return "admin/newsletter";
    }

    @GetMapping("/newsletter/delete/{id}")
    public String deleteSubscription(@PathVariable Long id) {
        newsletterService.deleteSubscription(id);
        return "redirect:/admin/newsletter";
    }
}
