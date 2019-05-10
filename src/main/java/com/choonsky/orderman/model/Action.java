package com.choonsky.orderman.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_order", insertable = false, updatable = false)
    private Integer idOrder;

    @Column(name = "id_state", insertable = false, updatable = false)
    private Integer idState;

    @Column(name = "id_message", insertable = false, updatable = false)
    private Integer idMessage;

    @Basic
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_order", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_state", nullable = false)
    private State state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_message", nullable = false)
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user", nullable = false)
    private User user;

    public Action() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdOrder() { return idOrder; }
    public void setIdOrder(Integer idOrder) { this.idOrder = idOrder; }

    public Integer getIdState() { return idState; }
    public void setIdState(Integer idState) { this.idState = idState; }

    public Integer getIdMessage() { return idMessage; }
    public void setIdMessage(Integer idMessage) { this.idMessage = idMessage; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public Message getMessage() { return message; }
    public void setMessage(Message message) { this.message = message; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return getId().equals(action.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", idState=" + idState +
                ", idMessage=" + idMessage +
                ", time=" + time +
                ", order=" + order +
                ", state=" + state +
                ", message=" + message +
                ", user=" + user +
                '}';
    }
}