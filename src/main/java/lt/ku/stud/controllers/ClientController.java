package lt.ku.stud.controllers;

import jakarta.validation.Valid;
import lt.ku.stud.entities.Client;
import lt.ku.stud.repositories.ClientRepository;
import lt.ku.stud.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public FileStorageService fileStorageService;

    @GetMapping("/")
    public String clients(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "clients_list";
    }

    @GetMapping("/new")
    public String newClient(Model client) {
        client.addAttribute("client", new Client());
        return "clients_new";
    }

    @PostMapping("/new")
    public String storeClient(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("file") MultipartFile file,
            @Valid @ModelAttribute("client") Client client,
            BindingResult bindingResult
    ) {
        Client c = new Client(name, surname, email, phone);
        if(bindingResult.hasErrors()) {
            return "client_new";
        }
        if(!file.isEmpty()) {
            client.setAgreement(file.getOriginalFilename());
        }
        clientRepository.save(c);
        if(!file.isEmpty()) {
            fileStorageService.store(file, Integer.toString(c.getId()));
        }
        return "redirect:/";
    }

    @GetMapping("/{id}/agreement")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable Integer id) {
        Client c = clientRepository.getReferenceById(id);
        Resource r = fileStorageService.loadFile(c.getId().toString());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + c.getAgreement() + "\"")
                .body(r);
    }

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable("id") Integer id,
            Model model
    ) {
        Client c = clientRepository.getReferenceById(id);
        model.addAttribute("client", c);
        return "clients_update";
    }

    @PostMapping("/update/{id}")
    public String save(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("file") MultipartFile file
    ) {

        Client c = clientRepository.getReferenceById(id);
        c.setName(name);
        c.setSurname(surname);
        c.setEmail(email);
        c.setPhone(phone);

        if(!file.isEmpty()) {
            c.setAgreement(file.getOriginalFilename());
        }
        clientRepository.save(c);
        if(!file.isEmpty()) {
            fileStorageService.store(file, Integer.toString(c.getId()));
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Integer id
    ) {
        clientRepository.deleteById(id);
        return "redirect:/";
    }

}
