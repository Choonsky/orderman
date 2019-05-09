package com.choonsky.orderman.web;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderTemplate {
    private Integer id;
    private LocalDateTime time;
    private String firstProductName;
    private int orderLinesQty;
    private boolean isSent;
    private boolean isApproved;
    private boolean isExecuting;
    private boolean isFinished;
    private int messagesQty;

    public OrderTemplate() {
    }

    public OrderTemplate(Integer id, LocalDateTime time, String firstProductName, int orderLinesQty, boolean isSent,
                         boolean isApproved, boolean isExecuting, boolean isFinished, int messagesQty) {
        this.id = id;
        this.time = time;
        this.firstProductName = firstProductName;
        this.orderLinesQty = orderLinesQty;
        this.isSent = isSent;
        this.isApproved = isApproved;
        this.isExecuting = isExecuting;
        this.isFinished = isFinished;
        this.messagesQty = messagesQty;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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

}
