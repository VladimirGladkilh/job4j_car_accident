package accident.control;

import accident.model.Accident;
import accident.repository.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {
    private final Store accidents;

    @Autowired
    public AccidentControl(Store accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.findAllTypes());
        model.addAttribute("rules", accidents.findAllRules());
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidents.findAllTypes());
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("rules", accidents.findAllRules());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] type_id = req.getParameterValues("type_id");
        if (type_id != null && type_id.length > 0) {
            accident.setType(accidents.findTypeById(Integer.parseInt(type_id[0])));
        }
        String[] ids = req.getParameterValues("rIds");
        if (ids != null && ids.length > 0) {
            accident.setRules(accidents.findAllRules().stream()
                    .filter(rule -> Arrays.stream(ids)
                            .anyMatch(s -> s.equalsIgnoreCase(String.valueOf(rule.getId()))))
                    .collect(Collectors.toSet()));
        }
        accidents.save(accident);
        return "redirect:/";
    }

    public static boolean contains(Collection<?> coll, Object o) {
        if (coll == null) return false;
        return coll.contains(o);
    }
}