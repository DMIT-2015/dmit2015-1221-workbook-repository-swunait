package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Shippers", schema = "WestWind")
public class Shipper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ShipperID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "CompanyName", nullable = false, length = 40)
    private String companyName;
    @Basic
    @Column(name = "Phone", nullable = false, length = 24)
    private String phone;
    @OneToMany(mappedBy = "shippersByShipVia")
    private Collection<Shipment> shipmentsByShipperId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer shipperId) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipper shipper = (Shipper) o;
        return Objects.equals(id, shipper.id) && Objects.equals(companyName, shipper.companyName) && Objects.equals(phone, shipper.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, phone);
    }

    public Collection<Shipment> getShipmentsByShipperId() {
        return shipmentsByShipperId;
    }

    public void setShipmentsByShipperId(Collection<Shipment> shipmentsByShipperId) {
        this.shipmentsByShipperId = shipmentsByShipperId;
    }
}
