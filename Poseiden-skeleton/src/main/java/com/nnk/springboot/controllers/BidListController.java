package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BidListController {
    // TODO: Inject Bid service
    @Autowired
    BidListService bidListService;

    @Autowired
    ISpringTemplateEngine template;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        List <BidList> bids = bidListService.getBids();
        model.addAttribute("bids", bids);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid, BindingResult result, Model model) {
        model.addAttribute("fields", result);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.setBid(bid);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        // TODO: get Bid by Id and to model then show to the form
        try {
            BidList bidList = bidListService.getBid(id);
            model.addAttribute("bidList", bidList);
            return "bidList/update";
        } catch (NoSuchElementException e){
            redirectAttributes.addFlashAttribute("message", "Cet id n'existe pas.");
            return "redirect:/bidList/list";
        }
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Long id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            return "bidList/update";
        }
        //maj du bidlist dans la bdd
        try {
            bidListService.updateBid(id, bidList);
            return "redirect:/bidList/list";
        } catch (NoSuchElementException e){
            model.addAttribute("message", "Cet id n'existe pas.");
            return "redirect:/bidList/list";
        }
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Long id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteBid(id);
        return "redirect:/bidList/list";
    }
}
