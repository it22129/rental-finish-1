package gr.hua.dit.ds.rental_management.Services;

import gr.hua.dit.ds.rental_management.Entities.PropertyApplication;
import gr.hua.dit.ds.rental_management.Entities.RentalApplication;
import gr.hua.dit.ds.rental_management.Repositories.PropertyApplicationRepository;
import gr.hua.dit.ds.rental_management.Repositories.PropertyRepository;
import gr.hua.dit.ds.rental_management.Repositories.RentalApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyApplicationService {

    private final PropertyApplicationRepository propertyApplicationRepository;

    public PropertyApplicationService(PropertyApplicationRepository propertyApplicationRepository) {
        this.propertyApplicationRepository = propertyApplicationRepository;
    }

    public List<PropertyApplication> getAllPropertyApplications() {
        return propertyApplicationRepository.findAll();
    }

    public PropertyApplication getPropertyApplicById(Integer id) {
        Optional<PropertyApplication> optionalPropertyAplic = propertyApplicationRepository.findById(id);
        return optionalPropertyAplic.orElseThrow(() -> new RuntimeException("Property Application not found"));
    }

    public void savePropertyApplic(PropertyApplication propertyApplic) {
        propertyApplicationRepository.save(propertyApplic);
    }

    public void updatePropertyApplication (int id, PropertyApplication propertyApplic ) {
        PropertyApplication existingPropertyApplic = getPropertyApplicById(id);
        existingPropertyApplic.setOwner(propertyApplic.getOwner());
        existingPropertyApplic.setStatus(propertyApplic.isStatus());
        propertyApplicationRepository.save(existingPropertyApplic);
    }

    public void deletePropertyApplic(int id) {
        propertyApplicationRepository.deleteById(id);
    }



}