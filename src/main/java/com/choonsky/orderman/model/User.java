package com.choonsky.orderman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User
{
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable=false)
    @NotEmpty()
    @NotBlank(message = "{errors.blank_name}")
    private String name;

    @Column(nullable=false, unique=true)
    @NotEmpty
    @Email(message="{errors.invalid_email}")
    private String email;

    @Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String password;

    @Column
    private String description;

    @Column
    private String phone;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JsonIgnore
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Action> actions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    public User() {}

    public User(String name, String email, String password, String description, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.phone = phone;
   }

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword() { return password; }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDescription() { return description; }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public List<Role> getRoles()
    {
        return roles;
    }
    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public Set<Action> getActions() { return actions; }
    public void setActions(Set<Action> actions) { this.actions = actions; }

    public Set<Order> getOrders() { return orders; }
    public void setOrders(Set<Order> orders) { this.orders = orders; }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" +
                password + ", description=" + description + ", phone=" + phone + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}