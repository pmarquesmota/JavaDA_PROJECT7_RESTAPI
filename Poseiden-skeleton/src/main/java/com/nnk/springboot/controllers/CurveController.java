package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {
    // TODO: Inject Curve Point service
    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/CurvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        List<CurvePoint> curvePoints = curvePointService.getCurvePoints();
        model.addAttribute("CurvePoints", curvePoints);
        return "curvePoint/list";
    }

    @GetMapping("/CurvePoint/add")
    public String addCurvePoint(CurvePoint curvePoint, BindingResult result, Model model) {
        model.addAttribute("fields", result);
        return "curvePoint/add";
    }

    @PostMapping("/CurvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.setCurvePoint(curvePoint);
        return "redirect:/CurvePoint/list";
    }

    @GetMapping("/CurvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        // TODO: get CurvePoint by Id and to model then show to the form
        try {
            CurvePoint curvePoint = curvePointService.getCurvePoint(id);
            model.addAttribute("curvePoint", curvePoint);
            return "curvePoint/update";
        } catch (NoSuchElementException e){
            redirectAttributes.addFlashAttribute("message", "Cet id n'existe pas.");
            return "redirect:/CurvePoint/list";
        }
    }

    @PostMapping("/CurvePoint/update/{id}")
    public String updateCurve(@PathVariable("id") Long id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        //maj du curvePoint dans la bdd
        try {
            curvePointService.updateCurvePoint(id, curvePoint);
            return "redirect:/CurvePoint/list";
        } catch (NoSuchElementException e){
            redirectAttributes.addFlashAttribute("message", "Cet id n'existe pas.");
            return "redirect:/CurvePoint/list";
        }
    }

    @GetMapping("/CurvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Long id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        curvePointService.deleteCurvePoint(id);
        return "redirect:/CurvePoint/list";
    }
}
