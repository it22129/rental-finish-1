package gr.hua.dit.ds.rental_management.Controllers;

import gr.hua.dit.ds.rental_management.Entities.RentalApplication;
import gr.hua.dit.ds.rental_management.Services.RentalApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rentals")
public class RentalApplicationController {
    private final RentalApplicationService rentalService;

    public RentalApplicationController(RentalApplicationService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public String listRentals(Model model) {
        List<RentalApplication> rentals = rentalService.getAllRentals();
        model.addAttribute("rentals", rentals);
        return "rental/rentals";
    }

    @GetMapping("/new")
    public String showNewRentalForm(Model model) {
        model.addAttribute("rental", new RentalApplication());
        return "rental/new_rental";
    }

    @PostMapping
    public String createRental(@ModelAttribute RentalApplication rental) {
        rentalService.saveRental(rental);
        return "redirect:/rentals";
    }

    @GetMapping("/edit/{id}")
    public String showEditRentalForm(@PathVariable Long id, Model model) {
        RentalApplication rental = rentalService.getRentalById(id);
        model.addAttribute("rental", rental);
        return "rental/edit_rental";
    }

    @PostMapping("/{id}")
    public String updateRental(@PathVariable Long id, @ModelAttribute RentalApplication rental) {
        rentalService.updateRental(id, rental);
        return "redirect:/rentals";
    }

    @GetMapping("/delete/{id}")
    public String deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return "redirect:/rentals";
    }
}
