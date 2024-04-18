package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.model.City;
import com.ecommerce.library.model.Country;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.service.CityService;
import com.ecommerce.library.service.CountryService;
import com.ecommerce.library.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        CustomerDto customer = customerService.getCustomer(username);
        List<Country> countryList = countryService.findAll();
        List<City> cities = cityService.findAll();
        model.addAttribute("customer", customer);
        model.addAttribute("countries", countryList);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Profile");
        model.addAttribute("page", "Profile");
        return "customer-information";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@Valid @ModelAttribute("customer")CustomerDto customerDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model,
                                Principal principal
    ){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        CustomerDto customer = customerService.getCustomer(username);
        List<Country> countryList = countryService.findAll();
        List<City> cities = cityService.findAll();
        model.addAttribute("countries", countryList);
        model.addAttribute("cities", cities);
        if(bindingResult.hasErrors()){
            return "customer-information";
        }
        customerService.update(customerDto);
        CustomerDto customerUpdate = customerService.getCustomer(principal.getName());
        redirectAttributes.addFlashAttribute("success", "Update Successfully!");
        model.addAttribute("customer", customerUpdate);
        return "redirect:/profile";
    }

    @GetMapping("change-password")
    public String changePassword(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title", "Change password");
        model.addAttribute("page", "Change password");
        return "change-password";
    }

    @PostMapping("change-password")
    public String changePasswordAciton(@RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword,
                                       @RequestParam("repeatNewPassword") String repeatNewPassword,
                                       RedirectAttributes redirectAttributes,
                                       Model model,
                                       Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            CustomerDto customer = customerService.getCustomer(principal.getName());
            if(passwordEncoder.matches(oldPassword, customer.getPassword())
                    && !passwordEncoder.matches(newPassword, oldPassword)
                    && !passwordEncoder.matches(newPassword, customer.getPassword())
                    && repeatNewPassword.equals(newPassword) && newPassword.length() >= 5){
                customer.setPassword(passwordEncoder.encode(newPassword));
                customerService.changePass(customer);
                redirectAttributes.addFlashAttribute("success", "Your password has been changed successfully!");
                return "redirect:/profile";
            }else{
                model.addAttribute("message", "Your password is wrong");
                return "change-password";
            }
        }
    }
}
