package gg.alex.spring.controlers;


import gg.alex.spring.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testbatch")
public class BatchController {
/*
    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(){
         return "batchFolder/batch";

    }

    @GetMapping("/without")
    public String withoutBatch(){
    personDAO.testMultipleUpdate();
    return "redirect:/people";
    }

    @GetMapping("/with")
    public String withBatch(){
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }*/
}
