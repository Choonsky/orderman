package com.choonsky.orderman.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_user", insertable = false, updatable = false)
    private Integer idUser;

    @Column(name = "id_state", insertable = false, updatable = false)
    private Integer idState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_state", nullable = false)
    private State state;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderLine> orderLines;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Action> actions;

    public Order() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public Integer getIdState() { return idState; }
    public void setIdState(Integer idState) { this.idState = idState; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public Set<OrderLine> getOrderLines() { return orderLines; }
    public void setOrderLines(Set<OrderLine> orderLines) { this.orderLines = orderLines; }

    public Set<Action> getActions() { return actions; }
    public void setActions(Set<Action> actions) { this.actions = actions; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
