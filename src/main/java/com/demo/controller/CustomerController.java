package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Order;
import com.demo.model.OrderDetail;
import com.demo.model.Product;
import com.demo.repo.*;
import com.demo.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    HttpSession session;

    @Autowired
    CartRepo cartService;

    @Autowired
    AccountRepo accountService;

    @Autowired
    CategoryRepo categoryService;

    @Autowired
    ProductRepo productService;

    @Autowired
    CartService cartService_;

    @Autowired
    CartDetailRepo cartDetailRepo;

    @ModelAttribute("cart")
    CartService getCartService() {
        return cartService_;
    }

    @Data
    @AllArgsConstructor
    public static class PriceRange {
        int id;
        int minValue;
        int maxValue;
        String display;
    }

    List<PriceRange> priceRangeList = Arrays.asList(
            new PriceRange(0, 0, Integer.MAX_VALUE, "Tất cả"),
            new PriceRange(1, 0, 10000000, "Dưới 10 triệu"),
            new PriceRange(2, 10000000, 20000000, "Từ 10-20 triệu"),
            new PriceRange(3, 20000000, Integer.MAX_VALUE, "Trên 20 triệu")
    );

    @RequestMapping("/")
    public String index(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") String categoryId,
            @RequestParam(defaultValue = "0") int priceRangeId,
            @RequestParam(defaultValue = "0") int p,
            Model model) {

        Sort sort = Sort.by(Sort.Direction.DESC, "price");
        Pageable page = PageRequest.of(p, 5, sort);
        int minPrice = priceRangeList.get(priceRangeId).minValue;
        int maxPrice = priceRangeList.get(priceRangeId).maxValue;

        model.addAttribute("priceRangeList", priceRangeList);
        model.addAttribute("categoryList", categoryService.findAll());
        if (categoryId.isEmpty()) {
            model.addAttribute("page", productService.findNotCategory(keyword, minPrice, maxPrice, page));
        } else {
            model.addAttribute("page", productService.findAll(keyword, categoryId, minPrice, maxPrice, page));
        }


        System.out.println("keyword=" + keyword);
        System.out.println("categoryId=" + categoryId);
        System.out.println("minPrice=" + minPrice);
        System.out.println("maxPrice=" + maxPrice);
        System.out.println("page=" + page);

        // TODO: Search & paginate

        return "customer/index";
    }

    @GetMapping("/detail/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id).get());
        for (Product p : productService.findAll()) {
            System.out.println(p);
        }
        return "customer/detail";
    }


    @RequestMapping("/about")
    public String about(Model model) {
        return "customer/about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            Account acc = accountService.findById(username).get();
            if (acc != null) {
                if (acc.getPassword().equals(password)) {
                    session.setAttribute("account", acc);
                    if (!acc.isAdmin()) {
                        return "redirect:/";
                    } else {
                        return "redirect:/admin";
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Không có người dùng");
        }
        model.addAttribute("message", "Tên đăng nhập/mật khẩu không đúng");
        return "login";
    }


    @RequestMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable int id) {
        cartService_.add(id);
        return "redirect:/cart";
    }

    @RequestMapping("/remove-cart/{id}")
    public String removeCart(@PathVariable int id) {
        cartService_.remove(id);
        if (cartService_.getTotal() == 0) {
            return "redirect:/";
        }
        return "redirect:/cart";
    }

    @RequestMapping("/update-cart/{id}")
    public String updateCart(@PathVariable int id, int quantity) {
        cartService_.update(id, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart() {
        return "customer/cart";
    }

    @GetMapping("/checkout")
    public String confirm() {
        if (session.getAttribute("account") == null) {
            return "redirect:/login";
        }
        return "customer/checkout";
    }

    @PostMapping("/purchase")
    public String purchase(@RequestParam String address) {
        System.out.println("address=" + address);
        System.out.println("items=" + cartService_.getItems());
        Account acc = (Account) session.getAttribute("account");
        if (acc != null) {
            Order order = new Order();
            order.setAccount(acc);
            order.setAddress(address);
            cartService.save(order);
            for (OrderDetail item : cartService_.getItems()) {
                item.setOrder(order);
                cartDetailRepo.save(item);
            }
            // TODO :clear cart
        }
        cartService_.clear();
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("account");
        return "redirect:/login";
    }
}
