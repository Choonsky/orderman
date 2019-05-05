package com.choonsky.orderman.model;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orderlines")
public class Orderline {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "id_product")
    private Integer idProduct;

    private BigDecimal amount;

    private String uoc;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdOrder() { return idOrder; }
    public void setIdOrder(Integer idOrder) { this.idOrder = idOrder; }

    public Integer getIdProduct() { return idProduct; }
    public void setIdProduct(Integer idProduct) { this.idProduct = idProduct; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getUoc() { return uoc; }
    public void setUoc(String uoc) { this.uoc = uoc; }

}
