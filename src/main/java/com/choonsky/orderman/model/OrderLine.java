package com.choonsky.orderman.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "orderlines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private BigDecimal amount;

    private String uoc;

    public OrderLine() {
    }

    public OrderLine(Order order, Product product, BigDecimal amount, String uoc) {
        this.order = order;
        this.product = product;
        this.amount = amount;
        this.uoc = uoc;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_order", referencedColumnName="id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_product", referencedColumnName="id")
    private Product product;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getUoc() { return uoc; }
    public void setUoc(String uoc) { this.uoc = uoc; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", order=" + this.order.getId() +
                ", product=" + this.product.getProductName() +
                ", amount=" + amount +
                ", uoc='" + uoc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return getId().equals(orderLine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
