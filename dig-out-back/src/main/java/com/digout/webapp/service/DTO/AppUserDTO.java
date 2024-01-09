package com.digout.webapp.service.DTO;

import java.util.Objects;

public class AppUserDTO implements DTO {

    private final int id;
    private final String nickname;
    private final String password;
    private final String email;
    private final String role;
    private final GraveOwnerDTO graveOwner;
    private final String photo;

    public AppUserDTO(int id, String nickname, String password, String email,
                      String role, GraveOwnerDTO graveOwner, String photo) {
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

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public GraveOwnerDTO getGraveOwner() {
        return graveOwner;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserDTO that = (AppUserDTO) o;
        return id == that.id && Objects.equals(nickname, that.nickname)
                && Objects.equals(password, that.password)
                && Objects.equals(email, that.email)
                && Objects.equals(role, that.role)
                && Objects.equals(graveOwner, that.graveOwner)
                && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, password, email, role, graveOwner, photo);
    }
}
