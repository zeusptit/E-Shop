package com.ecommerce.admin.controller;

import com.ecommerce.library.model.Order;
import com.ecommerce.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String getAll(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Order> orderList = orderService.findAllOrders();
        model.addAttribute("order", orderList);
        return "orders";
    }

    @RequestMapping(value = "/accept-order", method = {RequestMethod.GET, RequestMethod.PUT})
    public String acceptOrder(Long id, RedirectAttributes redirectAttributes, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        orderService.acceptOrder(id);
        redirectAttributes.addFlashAttribute("success", "Order Accepted");
        return "redirect:/orders";
    }

    @RequestMapping(value = "/cancel-order", method = {RequestMethod.GET, RequestMethod.PUT})
    public String cancelOrder(Long id, RedirectAttributes redirectAttributes, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        orderService.cancelOrder(id);
        redirectAttributes.addFlashAttribute("success", "Order canceled");
        return "redirect:/orders";
    }

    @GetMapping("/check-out")
    public String checkoutPage(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title", "Checkout");
        model.addAttribute("page", "Checkout");
        return "checkout";
    }
}
