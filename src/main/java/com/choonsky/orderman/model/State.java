package com.choonsky.orderman.model;

import javax.persistence.*;

@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "state_name")
    private String stateName;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStateName() { return stateName; }
    public void setStateName(String stateName) { this.stateName = stateName; }

}
