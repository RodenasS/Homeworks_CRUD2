package lt.ku.stud.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clients_groups")

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotEmpty(message = "Vardas yra privalomas")
    @Size(min = 3, max = 20, message = "Vardas turi būti 3-20 simbolių ilgio")
    private String name;
    @Column
    @NotEmpty(message = "Pavardė yra privaloma")
    @Size(min = 3, max = 25, message = "Pavardė turi būti 3-25 simbolių ilgio")
    private String surname;
    @Column
    @NotEmpty(message = "El. paštas yra privalomas")
    @Email(message = "El. paštas turi būti validus")
    private String email;
    @Column
    @NotEmpty(message = "Telefono numeris yra privalomas")
    @Size(max = 15, message = "Telefonas negali būti ilgesnis nei 15 simbolių")
    private String phone;
    @Column(length = 128, nullable = true)
    private String agreement = null;


    public Client() {
    }

    public Client(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname=" + surname +
                ", email=" + email +
                ", phone=" + phone +
                '}';
    }
}
