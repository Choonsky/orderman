package com.choonsky.orderman.web;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class OrderNew {
    private String[] productNames;
    private String[] productUocs;
    private String[] productAmounts;
    private String messageContent;
    private MultipartFile[] messageAttachments;
    private boolean isSent;

    public OrderNew(String[] productNames, String[] productUocs, String[] productAmounts, String messageContent, MultipartFile[] messageAttachments, boolean isSent) {
        this.productNames = productNames;
        this.productUocs = productUocs;
        this.productAmounts = productAmounts;
        this.messageContent = messageContent;
        this.messageAttachments = messageAttachments;
        this.isSent = isSent;
    }

    public OrderNew() {
    }

    public String[] getProductNames() {
        return productNames;
    }

    public void setProductNames(String[] productNames) {
        this.productNames = productNames;
    }

    public String[] getProductUocs() {
        return productUocs;
    }

    public void setProductUocs(String[] productUocs) {
        this.productUocs = productUocs;
    }

    public String[] getProductAmounts() {
        return productAmounts;
    }

    public void setProductAmounts(String[] productAmounts) {
        this.productAmounts = productAmounts;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public MultipartFile[] getMessageAttachments() {
        return messageAttachments;
    }

    public void setMessageAttachments(MultipartFile[] messageAttachments) {
        this.messageAttachments = messageAttachments;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderNew orderNew = (OrderNew) o;
        return getProductNames().equals(orderNew.getProductNames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductNames());
    }
}
