package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class RuleNameController {
    // TODO: Inject RuleName service
    @Autowired
    RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        List<RuleName> rulenames = ruleNameService.getRuleNames();
        model.addAttribute("rulenames", rulenames);

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName rulenames) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.setRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        // TODO: get RuleName by Id and to model then show to the form
        try {
            RuleName ruleName = ruleNameService.getRuleName(id);
            model.addAttribute("ruleName", ruleName);
            return "ruleName/update";
        } catch (NoSuchElementException e){
            redirectAttributes.addFlashAttribute("message", "Cet id n'existe pas.");
            return "redirect:/ruleName/list";
        }
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Long id, @Valid RuleName ruleName,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        //maj du rating dans la bdd
        try {
            ruleNameService.updateRuleName(id, ruleName);
            return "redirect:/ruleName/list";
        } catch (NoSuchElementException e){
            redirectAttributes.addFlashAttribute("message", "Cet id n'existe pas.");
            return "redirect:/ruleName/list";
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Long id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        ruleNameService.deleteRuleName(id);
        return "redirect:/ruleName/list";
    }
}
