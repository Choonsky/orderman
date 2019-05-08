package com.choonsky.orderman.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(length = 2048)
    private String content;

    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Action action;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Attachment> attachments;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Action getAction() { return action; }
    public void setAction(Action action) { this.action = action; }

    public Set<Attachment> getAttachments() { return attachments; }
    public void setAttachments(Set<Attachment> attachments) { this.attachments = attachments; }

}
