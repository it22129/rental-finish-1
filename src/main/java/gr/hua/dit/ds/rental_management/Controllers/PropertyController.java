package gr.hua.dit.ds.rental_management.Controllers;

import gr.hua.dit.ds.rental_management.Entities.Property;
import gr.hua.dit.ds.rental_management.Services.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public String listProperties(Model model) {
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("properties", properties);
        return "property/properties";
    }

    @GetMapping("/new")
    public String showNewPropertyForm(Model model) {
        model.addAttribute("property", new Property());
        return "property/new_property";
    }

    @PostMapping
    public String createProperty(@ModelAttribute Property property) {
        propertyService.saveProperty(property);
        return "redirect:/properties";
    }

    @GetMapping("/edit/{id}")
    public String showEditPropertyForm(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        model.addAttribute("property", property);
        return "property/edit_property";
    }

    @PostMapping("/{id}")
    public String updateProperty(@PathVariable Long id, @ModelAttribute Property property) {
        propertyService.updateProperty(id, property);
        return "redirect:/properties";
    }

    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return "redirect:/properties";
    }
}

