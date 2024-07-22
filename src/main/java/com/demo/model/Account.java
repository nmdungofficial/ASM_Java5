package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="Accounts")
public class Account implements Serializable {
    @Id
    @NotBlank(message = "Bạn chưa nhập tên người dùng")
    String username;

    @NotBlank(message = "Bạn chưa nhập mật khẩu")
    String password;

    @NotBlank(message = "Bạn chưa nhập Họ và Tên")
    String fullname;

    @NotBlank(message = "Bạn chưa nhập E-Mail")
    String email;

    boolean activated;
    boolean admin;
}
