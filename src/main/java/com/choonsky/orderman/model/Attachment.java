package com.choonsky.orderman.model;

import javax.persistence.*;

@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_message", insertable = false, updatable = false)
    private Integer idMessage;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "filesize")
    private Integer fileSize;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "file_content", columnDefinition = "BLOB", nullable = false)
    private byte[] fileContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_message", nullable = false)
    private Message message;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdMessage() { return idMessage; }
    public void setIdMessage(Integer idMessage) { this.idMessage = idMessage; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public Integer getFileSize() { return fileSize; }
    public void setFileSize(Integer fileSize) { this.fileSize = fileSize; }

    public byte[] getFileContent() { return fileContent; }
    public void setFileContent(byte[] fileContent) { this.fileContent = fileContent; }

    public Message getMessage() { return message; }
    public void setMessage(Message message) { this.message = message; }

}