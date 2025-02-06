package com.ipi.jva320.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalarieController {

        @GetMapping(value = "/salaries/1")
        public String getPatient(final ModelMap model) {
            model.put("id", 1);
            model.put("nom", "Jean");
            return "p";
        }
}
