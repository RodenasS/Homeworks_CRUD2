package lt.ku.stud.controllers;
import lt.ku.stud.entities.Workouts;
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
public class WorkoutsController {
    @Autowired
    public WorkoutsRepository WorkoutsRepository;
    @GetMapping("/workouts")
    public String workouts(Model model){
        List<Workouts> workouts=WorkoutsRepository.findAll();
        model.addAttribute("workouts", workouts);
        return "workouts_list";
    }
    @GetMapping("/workouts/new")
    public String newWorkout(){
        return "workouts_new";
    }

    @PostMapping("/workouts/new")
    public String storeWorkout(
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("places") String places,
            @RequestParam("location") String location
    ){
        Workouts w= new Workouts(name, date, places, location);
        WorkoutsRepository.save(w);
        return "redirect:/workouts";
    }
    @GetMapping("workouts/update/{id}")
    public String update(
            @PathVariable("id") Integer id,
            Model model
    ){
        Workouts w=WorkoutsRepository.getReferenceById(id);
        model.addAttribute("workout", w);
        return "workouts_update";
    }
    @PostMapping("workouts/update/{id}")
    public String save(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("places") String places,
            @RequestParam("location") String location
    ){
        Workouts w=WorkoutsRepository.getReferenceById(id);
        w.setName(name);
        w.setDate(date);
        w.setPlaces(places);
        w.setLocation(location);
        WorkoutsRepository.save(w);

        return "redirect:/workouts";
    }
    @GetMapping("workouts/delete/{id}")
    public  String delete(
            @PathVariable("id") Integer id
    ){
        WorkoutsRepository.deleteById(id);
        return "redirect:/workouts";
    }
}
