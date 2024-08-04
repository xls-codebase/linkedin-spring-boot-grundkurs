package linkedin.bbq_joint.controller;

import linkedin.bbq_joint.MenuItem;
import linkedin.bbq_joint.MenuItemRepository;
import linkedin.bbq_joint.Order;
import linkedin.bbq_joint.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MenuWebController {

    private final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;

    public MenuWebController(MenuItemRepository menuItemRepository, OrderRepository orderRepository) {
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Iterable<MenuItem> menuItems = menuItemRepository.findByOrderByDrinkDescNameDesc();
        model.addAttribute("pageTitle", "Menu");
        model.addAttribute("menuItems", menuItems);
        return "menu-card";
    }

    @GetMapping("/orders")
    public String provideOrderFormSelectionLists(Model model) {
        Iterable<MenuItem> drinkItems = menuItemRepository.findByDrinkOrderByNameDesc(true);
        Iterable<MenuItem> foodItems = menuItemRepository.findByDrinkOrderByNameDesc(false);
        model.addAttribute("pageTitle", "Place Order");
        model.addAttribute("foodItems", foodItems);
        model.addAttribute("drinkItems", drinkItems);
        return "order-form";
    }

    @PostMapping("/orders/place")
    public String placeOrder(@RequestParam(value = "drink") String drinkId,
                             @RequestParam(value = "food") String foodId,
                             @RequestParam String name,
                             Model model) {

        Optional<MenuItem> drink = menuItemRepository.findById(UUID.fromString(drinkId));
        Optional<MenuItem> food = menuItemRepository.findById(UUID.fromString(foodId));

        Order order = new Order();
        order.setName(name);
        drink.ifPresent(order::setDrink);
        food.ifPresent(order::setFood);
        orderRepository.save(order);

        int total = order.getDrink().getPrice() + order.getFood().getPrice();
        Iterable<String> menuItems = List.of(order.getDrink().getName(), order.getFood().getName());
        model.addAttribute("pageTitle", "Order Placed");
        model.addAttribute("name", order.getName());
        model.addAttribute("menuItems", menuItems);
        model.addAttribute("total", total);
        return "order-summary";
    }
}
