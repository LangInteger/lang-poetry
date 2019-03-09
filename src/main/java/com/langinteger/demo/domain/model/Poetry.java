package com.langinteger.demo.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "poetries")
public class Poetry {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(length = 10000)
  private String content;

  private String title;

  private Date createdAt;

  private Date updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "poet_id")
  private Poet poet;

  public Poet getPoet() {
    return poet;
  }

  public void setPoet(Poet poet) {
    this.poet = poet;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "Poetry{" +
        "id=" + id +
        ", content='" + content + '\'' +
        ", title='" + title + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
