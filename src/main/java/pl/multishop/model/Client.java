package pl.multishop.model;

import javax.persistence.*;

@Entity
@Table(name = "klienci")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numer klienta", nullable = false, unique = true, updatable = false)
    private int clientId;

    @Column(name = "imie_klienta", nullable = false, updatable = false)
    private String clientName;

    @Column(name = "nazwisko_klienta", nullable = false)
    private String clientSurname;

    @Column(name = "adres_klienta", nullable = false)
    private String clientAdress;

    @Column(name = "Miasto", nullable = false)
    private String clientCity;

    @Column(name = "Kraj", nullable = false)
    private String clientCountry;

    @Column(name = "email", nullable = false)
    private String clientEmail;

    @Column(name = "telefon", nullable = false)
    private String clientPhone;

    public Client(){
        super();
    }

    public Client(String clientName, String clientSurname, String clientAdress) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientAdress = clientAdress;
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
