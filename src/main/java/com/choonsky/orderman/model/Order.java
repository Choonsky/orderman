package com.choonsky.orderman.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
// @NamedQuery(name = "Order.saveByDefault",
//        query = " into u from User u where u.emailAddress = ?1")
// INSERT INTO `orderman`.`orders` (`id_user`, `id_state`) VALUES ('1', '1');

@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

/*
    @Column(name = "id_user", insertable = false, updatable = false)
    private Integer idUser;

    @Column(name = "id_state", insertable = false, updatable = false)
    private Integer idState;
*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user", referencedColumnName="id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_state", referencedColumnName="id")
    private State state;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderLine> orderLines;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Action> actions;

    public Order() {
    }

    public Order(User user, State state) {
        this.user = user;
        this.state = state;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
/*
    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public Integer getIdState() { return idState; }
    public void setIdState(Integer idState) { this.idState = idState; }
*/
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
//                ", idUser=" + idUser +
//                ", idState=" + idState +
                ", user=" + this.user.getEmail() +
                ", state=" + this.state.getStateName() +
//                ", orderLines=" + (orderLines. ? "empty" : orderLines.size()) +
//                ", actions=" + (actions.isEmpty() ? "empty" : actions.size()) +
                '}';
    }
}
