package linkedin.bbq_joint.controller;

import linkedin.bbq_joint.MenuItem;
import linkedin.bbq_joint.MenuItemRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu-items")
public class MenuRestController {

    private final MenuItemRepository menuItemRepository;

    public MenuRestController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @PostMapping
    public MenuItem menuItem(@RequestBody MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
}
