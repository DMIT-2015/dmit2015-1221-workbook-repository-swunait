package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ManifestItems", schema = "WestWind")
public class ManifestItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ManifestItemID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ShipmentID", nullable = false)
    private Integer shipmentId;
    @Basic
    @Column(name = "ProductID", nullable = false)
    private Integer productId;
    @Basic
    @Column(name = "ShipQuantity", nullable = false)
    private Short shipQuantity;
    @ManyToOne
    @JoinColumn(name = "ShipmentID", referencedColumnName = "ShipmentID", nullable = false, insertable = false, updatable = false)
    private Shipment shipmentsByShipmentId;
    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", nullable = false, insertable = false, updatable = false)
    private Product productsByProductId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Short getShipQuantity() {
        return shipQuantity;
    }

    public void setShipQuantity(Short shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManifestItem that = (ManifestItem) o;
        return Objects.equals(id, that.id) && Objects.equals(shipmentId, that.shipmentId) && Objects.equals(productId, that.productId) && Objects.equals(shipQuantity, that.shipQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipmentId, productId, shipQuantity);
    }

    public Shipment getShipmentsByShipmentId() {
        return shipmentsByShipmentId;
    }

    public void setShipmentsByShipmentId(Shipment shipmentsByShipmentId) {
        this.shipmentsByShipmentId = shipmentsByShipmentId;
    }

    public Product getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(Product productsByProductId) {
        this.productsByProductId = productsByProductId;
    }
}
