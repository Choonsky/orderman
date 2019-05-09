package com.choonsky.orderman.web;

import com.choonsky.orderman.model.OrderLine;

import java.util.ArrayList;
import java.util.Objects;

public class OrderTemplate {
    private Integer id;
    private String date;
    private String firstProductName;
    private int orderLinesQty;
    private boolean isSent;
    private boolean isApproved;
    private boolean isExecuting;
    private boolean isFinished;
    private int messagesQty;
    private ArrayList<OrderLine> orderLines;

    public OrderTemplate() {
    }

    public OrderTemplate(Integer id, String date, String firstProductName, int orderLinesQty, boolean isSent,
                         boolean isApproved, boolean isExecuting, boolean isFinished, int messagesQty, ArrayList<OrderLine> orderLines) {
        this.id = id;
        this.date = date;
        this.firstProductName = firstProductName;
        this.orderLinesQty = orderLinesQty;
        this.isSent = isSent;
        this.isApproved = isApproved;
        this.isExecuting = isExecuting;
        this.isFinished = isFinished;
        this.messagesQty = messagesQty;
        this.orderLines = orderLines;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTemplate that = (OrderTemplate) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstProductName() {
        return firstProductName;
    }

    public void setFirstProductName(String firstProductName) {
        this.firstProductName = firstProductName;
    }

    public int getOrderLinesQty() {
        return orderLinesQty;
    }

    public void setOrderLinesQty(int orderLinesQty) {
        this.orderLinesQty = orderLinesQty;
    }

    public boolean getSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public boolean getApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean getExecuting() {
        return isExecuting;
    }

    public void setExecuting(boolean executing) {
        isExecuting = executing;
    }

    public boolean getFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getMessagesQty() {
        return messagesQty;
    }

    public void setMessagesQty(int messagesQty) {
        this.messagesQty = messagesQty;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
