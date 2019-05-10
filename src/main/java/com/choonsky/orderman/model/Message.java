package com.choonsky.orderman.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(length = 2048)
    private String content;

    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Action action;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Attachment> attachments;

    public Message() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Action getAction() { return action; }
    public void setAction(Action action) { this.action = action; }

    public Set<Attachment> getAttachments() { return attachments; }
    public void setAttachments(Set<Attachment> attachments) { this.attachments = attachments; }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", action=" + action +
                ", attachments=" + attachments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return getId().equals(message.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
