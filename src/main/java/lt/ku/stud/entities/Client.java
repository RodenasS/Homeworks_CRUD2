package lt.ku.stud.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "clients")

public class Client implements UserDetails {
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
    @Column(nullable = true)
    private String agreement = null;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role="user";


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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> auth=new HashSet<>();
        auth.add(new SimpleGrantedAuthority(this.role));
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname=" + surname +
                ", email=" + email +
                ", phone=" + phone +
                ", agreement=" + agreement +
                ", username=" + username +
                ", password=" + password +
                ", role=" + role +
                '}';
    }
}
