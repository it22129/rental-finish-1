package gr.hua.dit.ds.rental_management.Repositories;

import gr.hua.dit.ds.rental_management.Entities.PropertyApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyApplicationRepository extends JpaRepository<PropertyApplication, Integer> {
}
