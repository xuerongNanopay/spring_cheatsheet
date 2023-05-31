package xrw.springsecurity.cheatsheet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name="authorities")
@IdClass(AuthorityId.class)
public class Authority {
  
  @Id
  @Column(name = "authority")
  private String authority;
  @Id@Column(name = "username")
  private String username;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "username")
  @ToString.Exclude
  private User user;
}
