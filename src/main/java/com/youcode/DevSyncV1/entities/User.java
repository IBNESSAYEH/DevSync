package com.youcode.DevSyncV1.entities;

import com.youcode.DevSyncV1.entities.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username" , nullable = false, unique = true)
    private String username;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "jeton_de_remplacement" , nullable = false)
    private String jetonParJour;

    @Column(name = "date_of_first_jeton")
    private LocalDateTime DateOfFirstReplacementOrder;

    @Column(name = "jeton_par_mois" , nullable = false)
    private String jetonParMois;

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @Column(name = "email" , nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role" , nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany
    @JoinColumn(name = "created_by")
    private List<Task> tasks;

    @OneToMany
    @JoinColumn(name = "assigned_to")
    private List<Task> assignedTasks;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String email, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getJetonParJour() {
        return jetonParJour;
    }

    public void setJetonParJour(String jetonParJour) {
        this.jetonParJour = jetonParJour;
    }

    public LocalDateTime getDateOfFirstReplacementOrder() {
        return DateOfFirstReplacementOrder;
    }

    public void setDateOfFirstReplacementOrder(LocalDateTime dateOfFirstReplacementOrder) {
        DateOfFirstReplacementOrder = dateOfFirstReplacementOrder;
    }

    public String getJetonParMois() {
        return jetonParMois;
    }

    public void setJetonParMois(String jetonParMois) {
        this.jetonParMois = jetonParMois;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    public User getManager() {
//        return manager;
//    }
//
//    public void setManager(User manager) {
//        this.manager = manager;
//    }
}
