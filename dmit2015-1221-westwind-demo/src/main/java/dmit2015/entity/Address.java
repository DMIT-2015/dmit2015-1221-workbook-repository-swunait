package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Addresses", schema = "WestWind")
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AddressID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Address", nullable = false, length = 60)
    private String address;
    @Basic
    @Column(name = "City", nullable = false, length = 15)
    private String city;
    @Basic
    @Column(name = "Region", nullable = true, length = 15)
    private String region;
    @Basic
    @Column(name = "PostalCode", nullable = true, length = 10)
    private String postalCode;
    @Basic
    @Column(name = "Country", nullable = false, length = 15)
    private String country;
    @OneToMany(mappedBy = "addressesByAddressId")
    private Collection<Customer> customersByAddressId;
    @OneToMany(mappedBy = "addressesByAddressId")
    private Collection<Employee> employeesByAddressId;
    @OneToMany(mappedBy = "addressesByShipAddressId")
    private Collection<Order> ordersByAddressId;
    @OneToMany(mappedBy = "addressesByAddressId")
    private Collection<Supplier> suppliersByAddressId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(id, address1.id) && Objects.equals(address, address1.address) && Objects.equals(city, address1.city) && Objects.equals(region, address1.region) && Objects.equals(postalCode, address1.postalCode) && Objects.equals(country, address1.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, region, postalCode, country);
    }

    public Collection<Customer> getCustomersByAddressId() {
        return customersByAddressId;
    }

    public void setCustomersByAddressId(Collection<Customer> customersByAddressId) {
        this.customersByAddressId = customersByAddressId;
    }

    public Collection<Employee> getEmployeesByAddressId() {
        return employeesByAddressId;
    }

    public void setEmployeesByAddressId(Collection<Employee> employeesByAddressId) {
        this.employeesByAddressId = employeesByAddressId;
    }

    public Collection<Order> getOrdersByAddressId() {
        return ordersByAddressId;
    }

    public void setOrdersByAddressId(Collection<Order> ordersByAddressId) {
        this.ordersByAddressId = ordersByAddressId;
    }

    public Collection<Supplier> getSuppliersByAddressId() {
        return suppliersByAddressId;
    }

    public void setSuppliersByAddressId(Collection<Supplier> suppliersByAddressId) {
        this.suppliersByAddressId = suppliersByAddressId;
    }
}
