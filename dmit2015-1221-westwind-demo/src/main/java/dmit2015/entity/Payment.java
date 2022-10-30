package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Payments", schema = "WestWind")
public class Payment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PaymentID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "PaymentDate", nullable = false)
    private LocalDate paymentDate;
    @Basic
    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;
    @Basic
    @Column(name = "PaymentTypeID", nullable = false)
    private Byte paymentTypeId;
    @Basic
    @Column(name = "OrderID", nullable = false)
    private Integer orderId;
    @Basic
    @Column(name = "TransactionID", nullable = false)
    private UUID transactionId;
    @Basic
    @Column(name = "ClearedDate", nullable = true)
    private LocalDate clearedDate;
    @ManyToOne
    @JoinColumn(name = "PaymentTypeID", referencedColumnName = "PaymentTypeID", nullable = false, insertable = false, updatable = false)
    private PaymentType paymentTypesByPaymentTypeId;
    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", nullable = false, insertable = false, updatable = false)
    private Order ordersByOrderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Byte paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getClearedDate() {
        return clearedDate;
    }

    public void setClearedDate(LocalDate clearedDate) {
        this.clearedDate = clearedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(amount, payment.amount) && Objects.equals(paymentTypeId, payment.paymentTypeId) && Objects.equals(orderId, payment.orderId) && Objects.equals(transactionId, payment.transactionId) && Objects.equals(clearedDate, payment.clearedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentDate, amount, paymentTypeId, orderId, transactionId, clearedDate);
    }

    public PaymentType getPaymentTypesByPaymentTypeId() {
        return paymentTypesByPaymentTypeId;
    }

    public void setPaymentTypesByPaymentTypeId(PaymentType paymentTypesByPaymentTypeId) {
        this.paymentTypesByPaymentTypeId = paymentTypesByPaymentTypeId;
    }

    public Order getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(Order ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }
}
