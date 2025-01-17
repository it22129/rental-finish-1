package gr.hua.dit.ds.rental_management.Services;

import gr.hua.dit.ds.rental_management.Entities.RentalApplication;
import gr.hua.dit.ds.rental_management.Repositories.RentalApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalApplicationService {

    private final RentalApplicationRepository rentalRepository;

    public RentalApplicationService(RentalApplicationRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<RentalApplication> getAllRentals() {
        return rentalRepository.findAll();
    }

    public RentalApplication getRentalById(Long id) {
        Optional<RentalApplication> optionalRental = rentalRepository.findById(id);
        return optionalRental.orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    public void saveRental(RentalApplication rental) {
        rentalRepository.save(rental);
    }

    public void updateRental(Long id, RentalApplication updatedRental) {
        RentalApplication existingRental = getRentalById(id);
        existingRental.setProperty(updatedRental.getProperty());
        existingRental.setTenants(updatedRental.getTenants());
        existingRental.setStatus(updatedRental.getStatus());
        existingRental.setOwner(updatedRental.getOwner());
        rentalRepository.save(existingRental);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
