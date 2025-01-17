package gr.hua.dit.ds.rental_management.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "rental_applications")
public class RentalApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 5)
    @Column(name = "rental_application_status")
    private boolean status;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="rentalAplic_tenantsId",
            joinColumns = @JoinColumn(name="rentalAplic_id"),
            inverseJoinColumns = @JoinColumn(name="tenant_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "rentalAplic_id"}) )
    private List<Tenant> tenants;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="owner_id")
    private Owner owner;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="property_id")
    private Property property;


    public RentalApplication(Owner owner, boolean status, List<Tenant> tenants, Property property) {
        this.owner = owner;
        this.status = status;
        this.tenants = tenants;
        this.property = property;
    }

    public RentalApplication() {
    }



    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }




    @Override
    public String toString() {
        return "RentalApplication{" +
                "id=" + id +
                ", status=" + status +
                ", tenants=" + tenants +
                ", owner=" + owner +
                ", property=" + property +
                '}';
    }
}


