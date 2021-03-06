package pl.multishop.model;

import javax.persistence.*;

/**
 * The POJO (Plain Old Java Object) class for user profile model.
 * @author Michał Stawarski
 * @version 1.0
 */
@Entity
@Table(name = "user_profile")
public class UserProfile extends AbstractDomainObject<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 15, unique = true, nullable = false)
    private String type = UserProfileType.USER.getUserProfileType();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String print() {
        return "Type: " + getType();
    }
}
