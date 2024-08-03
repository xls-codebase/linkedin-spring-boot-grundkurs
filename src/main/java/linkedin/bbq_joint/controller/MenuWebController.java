package linkedin.bbq_joint.controller;

import linkedin.bbq_joint.MenuItem;
import linkedin.bbq_joint.MenuItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuWebController {

    private final MenuItemRepository menuItemRepository;

    public MenuWebController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Iterable<MenuItem> menuItems = menuItemRepository.findByOrderByDrinkDescNameDesc();
        model.addAttribute("pageTitle", "Menu");
        model.addAttribute("menuItems", menuItems);
        return "menu-card";
    }
}
