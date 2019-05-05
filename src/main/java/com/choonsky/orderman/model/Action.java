package com.choonsky.orderman.model;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "id_state")
    private Integer idState;

    @Column(name = "id_message")
    private Integer idMessage;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp time;     // use toLocalDateTime(time)

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdOrder() { return idOrder; }
    public void setIdOrder(Integer idOrder) { this.idOrder = idOrder; }

    public Integer getIdState() { return idState; }
    public void setIdState(Integer idState) { this.idState = idState; }

    public Integer getIdMessage() { return idMessage; }
    public void setIdMessage(Integer idMessage) { this.idMessage = idMessage; }

    public Timestamp getTime() { return time; }
    public void setTime(Timestamp time) { this.time = time; }
}