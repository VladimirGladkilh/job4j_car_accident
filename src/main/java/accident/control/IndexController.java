package accident.control;


import accident.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private final AccidentService accidentService;

    @Autowired
    public IndexController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Владимир Гладких");
        List<String> list = List.of("Первый элемент", "Второй элемент", "Третий эдемент", "Четвертый элемент", "Ева Даллас");
        model.addAttribute("stringList", list);
        model.addAttribute("accidents", accidentService.getAll());
        return "index";
    }
}