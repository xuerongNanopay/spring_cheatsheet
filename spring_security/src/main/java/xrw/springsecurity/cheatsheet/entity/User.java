package xrw.springsecurity.cheatsheet.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
  @Id
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "enabled")
  private boolean enable;
  //MappedBy as mapping refer property name
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  //Key need to create a default list.
  private Collection<Authority> authorities = new ArrayList<Authority>();
}
