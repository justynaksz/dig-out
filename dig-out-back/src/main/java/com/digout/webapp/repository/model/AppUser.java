package com.digout.webapp.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column(name = "e_mail")
    private String email;

    @Column
    private String role;

    @ManyToOne()
    @JoinColumn(name = "grave_owner", referencedColumnName = "id")
    private GraveOwner graveOwner;

    @Column
    private String photo;

    public AppUser() {
    }

    public AppUser(int id, String nickname, String password, String email,
                   String role, GraveOwner graveOwner, String photo) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.graveOwner = graveOwner;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public GraveOwner getGraveOwner() {
        return graveOwner;
    }

    public void setGraveOwner(GraveOwner graveOwner) {
        this.graveOwner = graveOwner;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && Objects.equals(nickname, appUser.nickname)
                && Objects.equals(password, appUser.password)
                && Objects.equals(email, appUser.email)
                && Objects.equals(role, appUser.role)
                && Objects.equals(graveOwner, appUser.graveOwner)
                && Objects.equals(photo, appUser.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, password, email, role, graveOwner, photo);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", graveOwner=" + graveOwner +
                ", photo='" + photo + '\'' +
                '}';
    }
}