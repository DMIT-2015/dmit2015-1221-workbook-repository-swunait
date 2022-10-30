package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Customers", schema = "WestWind")
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CustomerID", nullable = false, length = 5)
    private String id;
    @Basic
    @Column(name = "CompanyName", nullable = false, length = 40)
    private String companyName;
    @Basic
    @Column(name = "ContactName", nullable = false, length = 30)
    private String contactName;
    @Basic
    @Column(name = "ContactTitle", nullable = true, length = 30)
    private String contactTitle;
    @Basic
    @Column(name = "ContactEmail", nullable = false, length = 50)
    private String contactEmail;
    @Basic
    @Column(name = "AddressID", nullable = false)
    private Integer addressId;
    @Basic
    @Column(name = "Phone", nullable = false, length = 24)
    private String phone;
    @Basic
    @Column(name = "Fax", nullable = true, length = 24)
    private String fax;
    @ManyToOne
    @JoinColumn(name = "AddressID", referencedColumnName = "AddressID", nullable = false, insertable = false, updatable = false)
    private Address addressesByAddressId;
    @OneToMany(mappedBy = "customersByCustomerId")
    private Collection<Order> ordersByCustomerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(companyName, customer.companyName) && Objects.equals(contactName, customer.contactName) && Objects.equals(contactTitle, customer.contactTitle) && Objects.equals(contactEmail, customer.contactEmail) && Objects.equals(addressId, customer.addressId) && Objects.equals(phone, customer.phone) && Objects.equals(fax, customer.fax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, contactName, contactTitle, contactEmail, addressId, phone, fax);
    }

    public Address getAddressesByAddressId() {
        return addressesByAddressId;
    }

    public void setAddressesByAddressId(Address addressesByAddressId) {
        this.addressesByAddressId = addressesByAddressId;
    }

    public Collection<Order> getOrdersByCustomerId() {
        return ordersByCustomerId;
    }

    public void setOrdersByCustomerId(Collection<Order> ordersByCustomerId) {
        this.ordersByCustomerId = ordersByCustomerId;
    }
}
