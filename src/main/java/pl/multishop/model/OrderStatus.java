package pl.multishop.model;

import javax.persistence.*;

/**
 * The POJO (Plain Old Java Object) class for user profile model.
 * @author Michał Stawarski
 * @version 1.0
 */
@Entity
@Table(name = "ORDER_STATUS")
@SuppressWarnings("serial")
public class OrderStatus extends AbstractDomainObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS", length = 10, unique = true, nullable = false)
    private String status = OrderStatusType.NEW.getStatus();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String print() {
        return "Status: " + getStatus();
    }
}
