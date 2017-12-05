package pl.multishop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "klient")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numer_klienta", nullable = false, unique = true, updatable = false)
    private int clientId;

    @Column(name = "imie_klienta", nullable = false)
    private String clientName;

    @Column(name = "nazwisko_klienta", nullable = false)
    private String clientSurname;

    @Column(name = "adres_klienta", nullable = false)
    private String clientAdress;

    @Column(name = "miasto", nullable = false)
    private String clientCity;

    @Column(name = "kraj", nullable = false)
    private String clientCountry;

    @Column(name = "email", nullable = false)
    private String clientEmail;

    @Column(name = "telefon", nullable = false)
    private String clientPhone;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    private Set<Product> products = new HashSet<Product>(0);

    public Client(){
        super();
    }

    public Client(String clientName, String clientSurname, Set<Product> products) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.products = products;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public void setClientAdress(String clientAdress) {
        this.clientAdress = clientAdress;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return clientId == client.clientId;
    }

    @Override
    public int hashCode() {
        return clientId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                '}';
    }
}
