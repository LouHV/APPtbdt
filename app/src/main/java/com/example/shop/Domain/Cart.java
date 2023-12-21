package com.example.shop.Domain;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
    private int total;
    private Long userId;
    private List<DetailCart>detailCart;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<DetailCart> getDetailCart() {
        return detailCart;
    }

    public void setDetailCart(List<DetailCart> detailCart) {
        this.detailCart = detailCart;
    }
}
