package com.futurenostics.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private  Long id;
    @Column(name = "USER_NAME",length = 10)
    private String name;
    @Column(name = "USER_EMAIL",length = 20)
    private String email;
    @Column(name = "USER_PWD",length = 64)
    private String password;
    @CreationTimestamp
    @Column(name = "SYS_CREAT_TS", length = 64)
    private ZonedDateTime createDate;

    @UpdateTimestamp
    @Column(name = "SYS_LAST_UPDT_TS", length = 64)
    private ZonedDateTime lastUpdatedUserDate;

    @PrePersist
    private void prePersistFnc() {
        var time = ZonedDateTime.now();
        setCreateDate(time);
    }

    @PreUpdate
    public void preUpdateFnc() {
        setLastUpdatedUserDate(ZonedDateTime.now());
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ROLE", joinColumns = @JoinColumn(name = "USR_ID",referencedColumnName = "USER_ID"))
    @Column(name = "USER_ROLE")
    private Set<String> roles;

}
