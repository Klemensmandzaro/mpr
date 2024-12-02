package pl.edu.pjatk.zaj2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pjatk.zaj2.service.MyRestService;
import pl.edu.pjatk.zaj2.service.Zwierze;

import java.util.List;

@Controller
public class MyViewController {
    private MyRestService myRestService;

    public MyViewController(MyRestService myRestService) {
        this.myRestService = myRestService;
    }

    @GetMapping("view/all")
    public String viewAll(Model model) {
        List<Zwierze> zwierzeList = myRestService.findAll();
        model.addAttribute("zwierzeList", zwierzeList);
        return "viewAll";
    }

    @GetMapping("view/form")
    public String viewAdd(Model model) {
        model.addAttribute("zwierze", new Zwierze());
        return "viewForm";
    }

    @PostMapping("zwierze/add")
    public String addZwierze(@ModelAttribute Zwierze zwierze) {
        myRestService.add(zwierze);
        return "redirect:/view/all";
    }

    @PostMapping("zwierze/zmien")
    public String updateZwierze(@ModelAttribute Zwierze zwierze) {
        myRestService.zmien(zwierze);
        return "redirect:/view/all";
    }

    @PostMapping("zwierze/usun")
    public String deleteZwierze(@ModelAttribute Zwierze zwierze) {
        myRestService.removeById(zwierze.getId());
        return "redirect:/view/all";
    }
}
