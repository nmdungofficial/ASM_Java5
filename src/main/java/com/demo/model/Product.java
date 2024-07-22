package com.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank(message = "Bạn chưa nhập tên sản phẩm")
    String name;

    @ManyToOne
    @JoinColumn(name = "Categoryid")
    @NotNull(message = "Bạn chưa chọn loại sản phẩm")
    Category category;

    @NotBlank(message = "Bạn chưa chọn hình ảnh sản phẩm")
    String image;

    @NotNull(message = "Bạn Chưa Nhập Đơn Giá")
    @Min(value = 1,message = "Giá phải là số dương và lớn hơn ")
    Integer price;

    boolean available;

    Date Createdate = new Date();
}
