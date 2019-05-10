package com.choonsky.orderman.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

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

    public Attachment() {
    }

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

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", idMessage=" + idMessage +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileContent=" + Arrays.toString(fileContent) +
                ", message=" + message +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}