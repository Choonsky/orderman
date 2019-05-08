package com.choonsky.orderman.model;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orderlines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_order", insertable = false, updatable = false)
    private Integer idOrder;

    @Column(name = "id_product", insertable = false, updatable = false)
    private Integer idProduct;

    private BigDecimal amount;

    private String uoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_order", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_product", nullable = false)
    private Product product;

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

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

}
