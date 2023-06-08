package gg.alex.spring.controlers;

import gg.alex.spring.dao.PersonDAO;
import gg.alex.spring.models.Person;
import gg.alex.spring.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonValidator personValidator, PersonDAO personDAO) {
        this.personValidator = personValidator;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){

        model.addAttribute("people", personDAO.index());


        return "people/index";
    }

    @GetMapping("/{person_id}")
    public String show(@PathVariable("person_id")int person_id, Model model){

        model.addAttribute("person", personDAO.show(person_id));

    return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){

        model.addAttribute("person", new Person());

        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()) return "people/new";

        personDAO.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{person_id}/edit")
    public String edit(Model model, @PathVariable("person_id") int person_id){
        model.addAttribute("person", personDAO.show(person_id));
        return "people/edit";
    }

    @PatchMapping("/{person_id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult , @PathVariable("person_id") int person_id){

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())return "people/edit";


        personDAO.update(person_id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{person_id}")
    public String delete(@PathVariable("person_id") int person_id){

        personDAO.delete(person_id);
        return "redirect:/people";

    }




}
