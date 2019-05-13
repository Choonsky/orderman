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

    @Basic
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_order", referencedColumnName="id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_state", referencedColumnName="id")
    private State state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_message", referencedColumnName="id")
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user", nullable = false)
    private User user;

    public Action() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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
                ", time=" + time +
                ", order=" + this.order.getId() +
                ", state=" + ((this.state == null) ? "null" : this.state.getStateName()) +
                ", message=" + ((this.message == null) ? "null" : this.message.getContent()) +
                ", user=" + this.user.getEmail() +
                '}';
    }
}