package com.langinteger.demo.domain.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "poets")
public class Poet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @OneToMany(mappedBy = "poet")
  private Set<Poetry> poetries;

  private String name;

  private Date createdAt;

  private Date updatedAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Set<Poetry> getPoetries() {
    return poetries;
  }

  public void setPoetries(Set<Poetry> poetries) {
    this.poetries = poetries;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
}
