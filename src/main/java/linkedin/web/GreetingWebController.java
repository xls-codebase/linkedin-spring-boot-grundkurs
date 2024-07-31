package linkedin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/www/greeting")
public class GreetingWebController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Dynamic Website");
        model.addAttribute("message", "America, I´m only getting started!");
        return "greeting";
    }
}
