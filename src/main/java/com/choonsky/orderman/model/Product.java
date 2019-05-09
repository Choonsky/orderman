package com.choonsky.orderman.model;

import javax.persistence.*;

import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    @Size(max=255)
    private String productName;

    @Column(name = "def_uoc", length = 9)
    @Size(max=9)
    private String defUoc;

    @Column(name = "def_manufacturer", length = 255)
    @Size(max=255)
    private String defManufacturer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderLine> orderLines;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDefUoc() { return defUoc; }
    public void setDefUoc(String defUoc) { this.defUoc = defUoc; }

    public String getDefManufacturer() { return defManufacturer; }
    public void setDefManufacturer(String defManufacturer) { this.defManufacturer = defManufacturer; }

    public Set<OrderLine> getOrderLines() { return orderLines; }
    public void setOrderLines(Set<OrderLine> orderLines) { this.orderLines = orderLines; }

}
