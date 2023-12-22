package com.digout.webapp.service.DTO;

import java.util.Objects;

public class AppUserDTO {

    private final int id;
    private final String nickname;
    private final String password;
    private final String email;
    private final String role;
    private final GraveOwnerDTO graveOwner;
    private final String avatar;

    public AppUserDTO(int id, String nickname, String password, String email,
                      String role, GraveOwnerDTO graveOwner, String avatar) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.graveOwner = graveOwner;
        this.avatar = avatar;
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

    public String getAvatar() {
        return avatar;
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
                && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, password, email, role, graveOwner, avatar);
    }
}
