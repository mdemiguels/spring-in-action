package tacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.OrderRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private OrderRepository orderRepository;

    public AdminController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/deleteOrders")
    public String deleteOrders() {
        orderRepository.deleteAll();
        return "redirect:/";
    }

}
