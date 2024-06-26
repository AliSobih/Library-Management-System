package com.library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Token {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true)
  private String token;

  @Column
  private boolean expired = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Patron patron;

  public Token(String token) {
    this.token = token;
  }
}
