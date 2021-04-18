package accident.control;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Владимир Гладких");
        List<String> list = List.of("Первый элемент", "Второй элемент", "Третий эдемент", "Четвертый элемент", "Ева Даллас");
        model.addAttribute("stringList", list);
        return "index";
    }
}