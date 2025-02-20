package com.ipi.jva320.Controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping(value = "/salaries/{id}")
    public String salarie(@PathVariable Long id, ModelMap model) {
        SalarieAideADomicile salarie = salarieAideADomicileService.getSalarie(id);
        if (salarie == null) {
            return "error"; // Assure-toi d'avoir une page d'erreur gérée
        }
        model.put("salarie", salarie);
        return "detail_Salarie";
    }



    @GetMapping(value = "/salaries/test")
    public String salarieTest(ModelMap model) {
        SalarieAideADomicile aide = new SalarieAideADomicile(
                "Jeannette Dupontelle",
                LocalDate.of(2021, 7, 1),
                LocalDate.now(),
                0, 0, 10, 1, 0
        );
        model.put("salarie", aide);
        return "detail_Salarie";
    }

    @GetMapping(value = "/salaries/aide/new")
    public String newSalarieForm(ModelMap model) {
        model.put("salarie", new SalarieAideADomicile());
        return "New_Salarie";
    }

    @PostMapping(value = "/salaries/save")
    public String createSalarie(SalarieAideADomicile salarie, ModelMap model) throws SalarieException {
        salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }

    @GetMapping(value = "/salaries")
    public String listSalaries(Model model) {
        List<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries();
        model.addAttribute("salaries", salaries);
        return "list";
    }


    @PostMapping(value = "/salaries/{id}")
    public String updateSalarie(@PathVariable Long id, @ModelAttribute SalarieAideADomicile salarie) throws SalarieException {
        salarie.setId(id); // Assure que l'ID est bien défini
        salarieAideADomicileService.updateSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + id; // Redirige vers la page de détail après mise à jour
    }

    @GetMapping(value = "/salaries/{id}/delete")
    public String deleteSalarie(@PathVariable Long id) throws SalarieException {
        salarieAideADomicileService.deleteSalarieAideADomicile(id);
        return "redirect:/salaries"; // Redirige vers la liste après suppression
    }

    @GetMapping(value = "/salaries/search")
    public String searchSalaries(@RequestParam("nom") String nom, Model model) {
        List<SalarieAideADomicile> salaries = salarieAideADomicileService.searchSalariesByPartialName(nom);
        if (salaries.isEmpty()) {
            model.addAttribute("message", "Aucun salarié trouvé avec ce nom.");
        }
        model.addAttribute("salaries", salaries);
        return "list"; // Assurez-vous que la vue "list" est configurée pour afficher la liste
    }




}

