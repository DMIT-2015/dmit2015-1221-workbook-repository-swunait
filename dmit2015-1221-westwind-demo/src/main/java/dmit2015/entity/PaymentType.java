package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PaymentTypes", schema = "WestWind")
public class PaymentType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PaymentTypeID", nullable = false)
    private Byte id;
    @Basic
    @Column(name = "PaymentTypeDescription", nullable = false, length = 40)
    private String paymentTypeDescription;
    @OneToMany(mappedBy = "paymentTypesByPaymentTypeId")
    private Collection<Payment> paymentsByPaymentTypeId;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getPaymentTypeDescription() {
        return paymentTypeDescription;
    }

    public void setPaymentTypeDescription(String paymentTypeDescription) {
        this.paymentTypeDescription = paymentTypeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentType that = (PaymentType) o;
        return Objects.equals(id, that.id) && Objects.equals(paymentTypeDescription, that.paymentTypeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentTypeDescription);
    }

    public Collection<Payment> getPaymentsByPaymentTypeId() {
        return paymentsByPaymentTypeId;
    }

    public void setPaymentsByPaymentTypeId(Collection<Payment> paymentsByPaymentTypeId) {
        this.paymentsByPaymentTypeId = paymentsByPaymentTypeId;
    }
}
