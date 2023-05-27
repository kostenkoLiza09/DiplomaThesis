package ua.com.sweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.sweet.repository.SweetRepository;
import ua.com.sweet.models.Sweet;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class SweetController {

    @Autowired
    private SweetRepository sweetRepository;

    @GetMapping("/view")
    private String viewMain(Model model) {
        Iterable<Sweet> sweets = sweetRepository.findAll();
        model.addAttribute("sweets", sweets);
        return "view_main";

    }

    @GetMapping("/view/add")
    private String sweetAdd(Model model) {
        return "sweet_add";

    }

    @PostMapping("/view/add")
    public String sweetProductAdd(@RequestParam String category, @RequestParam String name,
                                  @RequestParam int price, @RequestParam int amount,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date productionDate,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate) {
        Sweet sweet = new Sweet(category, name, productionDate, expirationDate, price, amount);
        sweetRepository.save(sweet);
        return "redirect:/view";
    }

    @GetMapping("/view/{id}")
    private String sweetDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Sweet> sweets = sweetRepository.findById(id);
        ArrayList<Sweet> sweetEntities = new ArrayList<>();
        sweets.ifPresent(sweetEntities::add);
        model.addAttribute("sweets", sweetEntities);
        return "sweet_details";
    }

    @GetMapping("/view/{id}/edit")
    private String sweetEdit(@PathVariable(value = "id") long id, Model model) {
        Optional<Sweet> sweets = sweetRepository.findById(id);
        ArrayList<Sweet> sweetEntities = new ArrayList<>();
        sweets.ifPresent(sweetEntities::add);
        model.addAttribute("sweets", sweetEntities);
        return "sweet_edit";
    }

    @PostMapping("/view/{id}/edit")
    private String sweetUpdate(@PathVariable(value = "id") long id, @RequestParam String category, @RequestParam String name,
                               @RequestParam int price, @RequestParam int amount,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date productionDate,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate) {
        Sweet sweet = sweetRepository.findById(id).orElseThrow();
        sweet.setCategory(category);
        sweet.setName(name);
        sweet.setPrice(price);
        sweet.setAmount(amount);
        sweet.setProductionDate(productionDate);
        sweet.setExpirationDate(expirationDate);
        sweetRepository.save(sweet);
        return "redirect:/view";
    }

    @PostMapping("/view/{id}/remove")
    private String sweetDelete(@PathVariable(value = "id") long id) {
        Sweet sweet = sweetRepository.findById(id).orElseThrow();
        sweetRepository.delete(sweet);
        return "redirect:/view";
    }
}