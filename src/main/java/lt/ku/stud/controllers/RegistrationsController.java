package lt.ku.stud.controllers;

import lt.ku.stud.entities.Client;
import lt.ku.stud.entities.Registrations;
import lt.ku.stud.entities.Workouts;
import lt.ku.stud.repositories.ClientRepository;
import lt.ku.stud.repositories.RegistrationsRepository;
import lt.ku.stud.repositories.WorkoutsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class RegistrationsController {
    @Autowired
    public RegistrationsRepository registrationsRepository;
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public WorkoutsRepository workoutsRepository;
    @GetMapping("/registrations")
    public String workouts(Model model){
        List<Registrations> registrations=registrationsRepository.findAll();
        model.addAttribute("registrations", registrations);
        return "registrations_list";
    }
    @GetMapping("/registrations/new")
    public String newRegistration(Model model){
        List<Client> clients=clientRepository.findAll();
        List<Workouts> workouts=workoutsRepository.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("workouts", workouts);
        return "registrations_new";
    }

    @PostMapping("/registrations/new")
    public String storeRegistration(
            @RequestParam("client_id") Integer client_id,
            @RequestParam("workout_id") Integer workout_id,
            @RequestParam("registrationDate") String registrationDate
    ){
        Client c=clientRepository.getReferenceById(client_id);
        Workouts w = workoutsRepository.getReferenceById(workout_id);
        Registrations r=new Registrations(registrationDate, w, c);
        registrationsRepository.save(r);
        return "redirect:/registrations";
    }
    @GetMapping("/registrations/delete/{id}")
    public  String delete(
            @PathVariable("id") Integer id
    ){
        registrationsRepository.deleteById(id);
        return "redirect:/registrations";
    }
}
