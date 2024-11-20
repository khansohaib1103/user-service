package com.futurenostics.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private  Long id;
    @Column(name = "USER_NAME",length = 10)
    private String userName;
    @Column(name = "USER_EMAIL",length = 20)
    private String userEmail;
    @Column(name = "USER_PWD",length = 64)
    private String userPassword;
    @CreationTimestamp
    @Column(name = "SYS_CREAT_TS", length = 64)
    private ZonedDateTime userCreated;

    @UpdateTimestamp
    @Column(name = "SYS_LAST_UPDT_TS", length = 64)
    private ZonedDateTime userUpdated;

    @PrePersist
    private void prePersistFnc() {
        var time = ZonedDateTime.now();
        setUserCreated(time);
    }

    @PreUpdate
    public void preUpdateFnc() {
        setUserUpdated(ZonedDateTime.now());
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ROLE", joinColumns = @JoinColumn(name = "USR_ID",referencedColumnName = "USER_ID"))
    @Column(name = "USER_ROLE")
    private Set<String> roles;

}
