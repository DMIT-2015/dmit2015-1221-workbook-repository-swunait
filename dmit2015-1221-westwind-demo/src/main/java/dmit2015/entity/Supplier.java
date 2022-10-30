package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Suppliers", schema = "WestWind")
public class Supplier implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SupplierID", nullable = false)
    private Integer id;
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
    @Column(name = "Email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "AddressID", nullable = false)
    private Integer addressId;
    @Basic
    @Column(name = "Phone", nullable = false, length = 24)
    private String phone;
    @Basic
    @Column(name = "Fax", nullable = true, length = 24)
    private String fax;
    @OneToMany(mappedBy = "suppliersBySupplierId")
    private Collection<Product> productsBySupplierId;
    @ManyToOne
    @JoinColumn(name = "AddressID", referencedColumnName = "AddressID", nullable = false, insertable = false, updatable = false)
    private Address addressesByAddressId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer supplierId) {
        this.id = supplierId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Supplier supplier = (Supplier) o;
        return Objects.equals(id, supplier.id) && Objects.equals(companyName, supplier.companyName) && Objects.equals(contactName, supplier.contactName) && Objects.equals(contactTitle, supplier.contactTitle) && Objects.equals(email, supplier.email) && Objects.equals(addressId, supplier.addressId) && Objects.equals(phone, supplier.phone) && Objects.equals(fax, supplier.fax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, contactName, contactTitle, email, addressId, phone, fax);
    }

    public Collection<Product> getProductsBySupplierId() {
        return productsBySupplierId;
    }

    public void setProductsBySupplierId(Collection<Product> productsBySupplierId) {
        this.productsBySupplierId = productsBySupplierId;
    }

    public Address getAddressesByAddressId() {
        return addressesByAddressId;
    }

    public void setAddressesByAddressId(Address addressesByAddressId) {
        this.addressesByAddressId = addressesByAddressId;
    }
}
