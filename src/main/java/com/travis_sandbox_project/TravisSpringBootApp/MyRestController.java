package com.travis_sandbox_project.TravisSpringBootApp;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

@RestController
@RequestMapping("wizardwarrior")
public class MyRestController {
    // Example usage:   http://localhost:8080/wizardwarrior/greeting
    @GetMapping("/greeting")
    public String greeting() {
        return "Hello Travis!";
    }

    // Example usage:   curl http://localhost:8080/wizardwarrior/wizards
    @GetMapping("/wizards")
    public Iterator wizards() {
        return new ArrayList<String>(Arrays.asList("Gandolf", "The White Wizard", "Fire Wizard", "Water Wizard")).iterator();
    }

    // Example usage:   curl -X POST --data "alias=lightening-Wizard"  http://localhost:8080/wizardwarrior/wizardtype -i
    // This curl usage puts the data as url parameters
    @PostMapping("/wizardtype")
    public String wizardType(@RequestParam String alias) {
        return "Added wizard alias: %s".formatted(alias);
    }

    //  Example usage:   curl -X DELETE http://localhost:8080/wizardwarrior/wizards -i
    @DeleteMapping("/wizards")
    public String deleteWizards() {
        return "Attempted to delete an arbitrary wizard";
    }
}
