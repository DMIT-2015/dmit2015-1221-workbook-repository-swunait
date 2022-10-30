package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Shipments", schema = "WestWind")
public class Shipment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ShipmentID", nullable = false)
    private Integer shipmentId;
    @Basic
    @Column(name = "OrderID", nullable = false)
    private Integer orderId;
    @Basic
    @Column(name = "ShippedDate", nullable = false)
    private LocalDate shippedDate;
    @Basic
    @Column(name = "ShipVia", nullable = false)
    private Integer shipVia;
    @Basic
    @Column(name = "FreightCharge", nullable = false)
    private BigDecimal freightCharge;
    @Basic
    @Column(name = "TrackingCode", nullable = true, length = 128)
    private String trackingCode;
    @OneToMany(mappedBy = "shipmentsByShipmentId")
    private Collection<ManifestItem> manifestItemsByShipmentId;
    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", nullable = false, insertable = false, updatable = false)
    private Order ordersByOrderId;
    @ManyToOne
    @JoinColumn(name = "ShipVia", referencedColumnName = "ShipperID", nullable = false, insertable = false, updatable = false)
    private Shipper shippersByShipVia;

    public Integer getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getShipVia() {
        return shipVia;
    }

    public void setShipVia(Integer shipVia) {
        this.shipVia = shipVia;
    }

    public BigDecimal getFreightCharge() {
        return freightCharge;
    }

    public void setFreightCharge(BigDecimal freightCharge) {
        this.freightCharge = freightCharge;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return Objects.equals(shipmentId, shipment.shipmentId) && Objects.equals(orderId, shipment.orderId) && Objects.equals(shippedDate, shipment.shippedDate) && Objects.equals(shipVia, shipment.shipVia) && Objects.equals(freightCharge, shipment.freightCharge) && Objects.equals(trackingCode, shipment.trackingCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipmentId, orderId, shippedDate, shipVia, freightCharge, trackingCode);
    }

    public Collection<ManifestItem> getManifestItemsByShipmentId() {
        return manifestItemsByShipmentId;
    }

    public void setManifestItemsByShipmentId(Collection<ManifestItem> manifestItemsByShipmentId) {
        this.manifestItemsByShipmentId = manifestItemsByShipmentId;
    }

    public Order getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(Order ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }

    public Shipper getShippersByShipVia() {
        return shippersByShipVia;
    }

    public void setShippersByShipVia(Shipper shippersByShipVia) {
        this.shippersByShipVia = shippersByShipVia;
    }
}
