package com.ipi.jva320.Controller;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping(value = "/salaries/{id}")
    public String salarie(@PathVariable Long id, ModelMap model) {
        SalarieAideADomicile salarie = salarieAideADomicileService.getSalarie(id);
        model.put("salarie", salarie);
        return "detail_Salarie";
    }
}
