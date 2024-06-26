package com.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "patron")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patron implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name")
    @NotBlank
    @Size(min = 3, max = 20, message = "First Name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-z]+$", message = "First Name must not contains numbers or special character")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(min = 3, max = 20, message = "Last Name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-z]+$", message = "Last Name must not contains numbers or special character")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Size(min = 8, message = "password must be at lest 8 character long")
    @NotBlank
    private String password;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\d+$")
    @NotBlank
    private String phoneNumber;

    @OneToMany(mappedBy = "patron")
    @JsonIgnore
    private List<BorrowingRecord> borrowingRecords;

    @OneToMany(mappedBy = "patron", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Token> tokens = new HashSet<>();

    //convenience method
    public void addBorrowingRecord(BorrowingRecord record) {
        record.setPatron(this);
        borrowingRecords.add(record);
    }
    public void addToken(Token token) {
        if (tokens == null) {
            tokens = new HashSet<>();
        }
        token.setPatron(this);
        this.tokens.add(token);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_PATRON"));

    }

    @Override
    public String getUsername() {
        return email;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
