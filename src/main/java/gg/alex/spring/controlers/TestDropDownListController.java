package gg.alex.spring.controlers;

import gg.alex.spring.dao.PersonDAO;
import gg.alex.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/choose_test")
public class TestDropDownListController {

    private final PersonDAO personDAO;

    @Autowired
    public TestDropDownListController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String choosePage(Model model, @ModelAttribute("person")Person person){

        model.addAttribute("people", personDAO.index());

        return "dropDownTestPage";

    }
    @PatchMapping("/choose")
    public String makeChoose(@ModelAttribute("person") Person person){
        System.out.println(person.getPerson_id());

        return "redirect:/people";
    }

}
