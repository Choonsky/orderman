package com.choonsky.orderman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" +
                password + ", description=" + description + ", phone=" + phone + '}';
    }
}