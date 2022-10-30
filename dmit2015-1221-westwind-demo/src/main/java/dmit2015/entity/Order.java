package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Orders", schema = "WestWind")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OrderID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "SalesRepID", nullable = true)
    private Integer salesRepId;
    @Basic
    @Column(name = "CustomerID", nullable = false, length = 5)
    private String customerId;
    @Basic
    @Column(name = "OrderDate", nullable = true)
    private LocalDate orderDate;
    @Basic
    @Column(name = "RequiredDate", nullable = true)
    private LocalDate requiredDate;
    @Basic
    @Column(name = "PaymentDueDate", nullable = true)
    private LocalDate paymentDueDate;
    @Basic
    @Column(name = "Freight", nullable = true)
    private BigDecimal freight;
    @Basic
    @Column(name = "Shipped", nullable = false)
    private Boolean shipped;
    @Basic
    @Column(name = "ShipName", nullable = true, length = 40)
    private String shipName;
    @Basic
    @Column(name = "ShipAddressID", nullable = true)
    private Integer shipAddressId;
    @Basic
    @Column(name = "Comments", nullable = true, length = 250)
    private String comments;
    @OneToMany(mappedBy = "ordersByOrderId")
    private Collection<OrderDetail> orderDetailsByOrderId;
    @ManyToOne
    @JoinColumn(name = "SalesRepID", referencedColumnName = "EmployeeID", insertable = false, updatable = false)
    private Employee employeesBySalesRepId;
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", nullable = false, insertable = false, updatable = false)
    private Customer customersByCustomerId;
    @ManyToOne
    @JoinColumn(name = "ShipAddressID", referencedColumnName = "AddressID", insertable = false, updatable = false)
    private Address addressesByShipAddressId;
    @OneToMany(mappedBy = "ordersByOrderId")
    private Collection<Payment> paymentsByOrderId;
    @OneToMany(mappedBy = "ordersByOrderId")
    private Collection<Shipment> shipmentsByOrderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Integer getShipAddressId() {
        return shipAddressId;
    }

    public void setShipAddressId(Integer shipAddressId) {
        this.shipAddressId = shipAddressId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(salesRepId, order.salesRepId) && Objects.equals(customerId, order.customerId) && Objects.equals(orderDate, order.orderDate) && Objects.equals(requiredDate, order.requiredDate) && Objects.equals(paymentDueDate, order.paymentDueDate) && Objects.equals(freight, order.freight) && Objects.equals(shipped, order.shipped) && Objects.equals(shipName, order.shipName) && Objects.equals(shipAddressId, order.shipAddressId) && Objects.equals(comments, order.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salesRepId, customerId, orderDate, requiredDate, paymentDueDate, freight, shipped, shipName, shipAddressId, comments);
    }

    public Collection<OrderDetail> getOrderDetailsByOrderId() {
        return orderDetailsByOrderId;
    }

    public void setOrderDetailsByOrderId(Collection<OrderDetail> orderDetailsByOrderId) {
        this.orderDetailsByOrderId = orderDetailsByOrderId;
    }

    public Employee getEmployeesBySalesRepId() {
        return employeesBySalesRepId;
    }

    public void setEmployeesBySalesRepId(Employee employeesBySalesRepId) {
        this.employeesBySalesRepId = employeesBySalesRepId;
    }

    public Customer getCustomersByCustomerId() {
        return customersByCustomerId;
    }

    public void setCustomersByCustomerId(Customer customersByCustomerId) {
        this.customersByCustomerId = customersByCustomerId;
    }

    public Address getAddressesByShipAddressId() {
        return addressesByShipAddressId;
    }

    public void setAddressesByShipAddressId(Address addressesByShipAddressId) {
        this.addressesByShipAddressId = addressesByShipAddressId;
    }

    public Collection<Payment> getPaymentsByOrderId() {
        return paymentsByOrderId;
    }

    public void setPaymentsByOrderId(Collection<Payment> paymentsByOrderId) {
        this.paymentsByOrderId = paymentsByOrderId;
    }

    public Collection<Shipment> getShipmentsByOrderId() {
        return shipmentsByOrderId;
    }

    public void setShipmentsByOrderId(Collection<Shipment> shipmentsByOrderId) {
        this.shipmentsByOrderId = shipmentsByOrderId;
    }
}
