package com.choonsky.orderman.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "id_state")
    private Integer idState;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public Integer getIdState() { return idState; }
    public void setIdState(Integer idState) { this.idState = idState; }

}
