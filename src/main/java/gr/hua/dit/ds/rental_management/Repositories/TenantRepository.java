package gr.hua.dit.ds.rental_management.Repositories;

import gr.hua.dit.ds.rental_management.Entities.PropertyApplication;
import gr.hua.dit.ds.rental_management.Entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant,Integer> {

    Optional<Tenant> findByLastName(String last_name);
    Boolean existsByLastName(String last_name);

}
