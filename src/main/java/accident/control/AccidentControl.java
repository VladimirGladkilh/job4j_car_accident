package accident.control;

import accident.model.Accident;
import accident.repository.AccidentRepository;
import accident.repository.AccidentTypeRepository;
import accident.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    @Autowired
    public AccidentControl(AccidentRepository accidents, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidents;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentTypeRepository.findAll());
        model.addAttribute("rules", ruleRepository.findAll());
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidentTypeRepository.findAll());
        model.addAttribute("accident", accidentRepository.findById(id).orElse(null));
        model.addAttribute("rules", ruleRepository.findAll());
        Optional<Accident> accident = accidentRepository.findById(id);
        model.addAttribute("selRules", accident.<Object>map(Accident::getRules).orElse(null));
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] type_id = req.getParameterValues("type_id");
        if (type_id != null && type_id.length > 0) {
            accident.setType(accidentTypeRepository.findById(Integer.parseInt(type_id[0])).orElse(null));
        }
        String[] ids = req.getParameterValues("rIds");
        if (ids != null && ids.length > 0) {
            accident.setRules(Arrays.stream(ids)
                    .map(s -> ruleRepository.findById(Integer.parseInt(s)).orElse(null))
                    .collect(Collectors.toSet()));
        }
        accidentRepository.save(accident);
        return "redirect:/";
    }

}