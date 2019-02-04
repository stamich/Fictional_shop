package pl.multishop.model;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The POJO (Plain Old Java Object) class for user model.
 * @author Michał Stawarski
 * @version 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`USER`")
@SuppressWarnings("serial")
public class User extends AbstractDomainObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "sso_id", unique = true, nullable = false)
    private String sSoId; //sSoId = single sign on id

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "NICKNAME", unique = true, nullable = false)
    private String nickName;

    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_user_profile",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_profile_id") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsSoId() {
        return sSoId;
    }

    public void setsSoId(String sSoId) {
        this.sSoId = sSoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    @Override
    public String print() {
        return "User: " + getId() + ", " + getNickName() + " created: " + getCreatedAt();
    }
}
