package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repo.AccountRepo;
import com.demo.repo.CategoryRepo;
import com.demo.repo.ProductRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    ProductRepo productService;

    @Autowired
    HttpSession session;

    @Autowired
    AccountRepo accountService;

    @Autowired
    CategoryRepo categoryService;


    //  Category
    @GetMapping("/admin")
    public String listCategory(Model model, @RequestParam(defaultValue = "") String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        model.addAttribute("page", categoryService.findAll(keyword, sort));
        return "admin/category/list";
    }

    @GetMapping("/admin/category/create")
    public String view_createCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("labelCategory", "Thêm Mới");
        return "admin/category/form";
    }

    @GetMapping("/admin/category/update/{id}")
    public String view_editCategory(Model model, @PathVariable String id) {
        model.addAttribute("labelCategory", "Chỉnh Sửa");
        model.addAttribute("category", categoryService.findById(id));
        return "admin/category/form";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        categoryService.delete(categoryService.findById(id).get());
        return "redirect:/admin";
    }


    @PostMapping("/admin/category/create")
    public String createCategory(@Valid @ModelAttribute("category") Category c, BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            model.addAttribute("labelCategory", "Thêm Mới");
            return "admin/category/form";
        } else {
            try {
                categoryService.findById(c.getId()).get();
                model.addAttribute("error", "ID đã tồn tại");
                return "admin/category/form";
            } catch (Exception e) {
                categoryService.save(c);
                return "redirect:/admin";
            }
        }
    }

    @PostMapping("/admin/category/update/{id}")
    public String editCategory(@Valid @ModelAttribute("category") Category c, BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            model.addAttribute("labelCategory", "Chỉnh Sửa");
            return "admin/category/form";
        } else {
            categoryService.save(c);
            return "redirect:/admin";
        }
    }

    //  Product
    @GetMapping("/admin/product")
    public String listProduct(Model model, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int p) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable page = PageRequest.of(p, 5, sort);
        model.addAttribute("page", productService.findBySearch(keyword, page));
        model.addAttribute("keyword", keyword);
        return "admin/product/list";
    }

    @GetMapping("/admin/product/create")
    public String view_createProduct(Model model) {
        model.addAttribute("listCategory", categoryService.findAll());
        model.addAttribute("product", new Product());
        return "admin/product/form";
    }

    @GetMapping("/admin/product/update/{id}")
    public String editProduct(Model model, @PathVariable int id) {
        model.addAttribute("listCategory", categoryService.findAll());
        Product p = productService.findById(id).get();
        session.setAttribute("img", p.getImage());
        model.addAttribute("product", p);
        return "admin/product/edit";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.delete(productService.findById(id).get());
        return "redirect:/admin/product";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@Valid @ModelAttribute("product") Product p, BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            model.addAttribute("error", "Một số trường chưa hợp lệ");
            model.addAttribute("listCategory", categoryService.findAll());
        } else {
            productService.save(p);
            return "redirect:/admin/product";
        }
        return "admin/product/form";
    }

    @PostMapping("/admin/product/update/{id}")
    public String editAccount(@Valid @ModelAttribute("product") Product p, BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            model.addAttribute("error", "Mội số trường chưa hợp lệ.");
            model.addAttribute("listCategory", categoryService.findAll());
        } else {
            productService.save(p);
            return "redirect:/admin/product";
        }
        return "admin/product/edit";
    }

    //  Account
    @GetMapping("/admin/account")
    public String listAccount(Model model, @RequestParam(defaultValue = "0") int p, @RequestParam(defaultValue = "") String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "username");
        System.out.println(keyword);
        Pageable page = PageRequest.of(p, 5, sort);
        model.addAttribute("page", accountService.findByKeyword(keyword, page));
        return "admin/account/list";
    }

    @GetMapping("/admin/account/create")
    public String createAccount(Model model) {
        model.addAttribute("account", new Account());
        return "admin/account/form";
    }

    @GetMapping("/admin/account/delete/{username}")
    public String deleteAccount(@PathVariable String username, Model model) {
        accountService.delete(accountService.findById(username).get());
        return "redirect:/admin/account";
    }

    @GetMapping("/admin/account/update/{username}")
    public String view_editAccount(@PathVariable String username, Model model) {
        model.addAttribute("account", accountService.findById(username).get());
        return "admin/account/edit";
    }

    @PostMapping("/admin/account/create")
    public String createProduct(@Valid @ModelAttribute("account") Account account, BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            model.addAttribute("error", "Mội số trường chưa hợp lệ.");
        } else {
            try {
                accountService.findById(account.getUsername()).get();
                model.addAttribute("error", "Tên tài khoản đã tồn tại");
            } catch (Exception e) {
                accountService.save(account);
                return "redirect:/admin/account";
            }
        }
        return "admin/account/form";
    }

    @PostMapping("/admin/account/update/{username}")
    public String editAccount(@Valid @ModelAttribute("account") Account account, BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            model.addAttribute("error", "Mội số trường chưa hợp lệ.");
        } else {
            accountService.save(account);
            model.addAttribute("error", "Sửa Thành Công");
            return "redirect:/admin/account";
        }
        return "admin/account/edit";
    }
}
