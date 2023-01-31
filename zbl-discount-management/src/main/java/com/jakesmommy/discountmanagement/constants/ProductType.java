package com.jakesmommy.discountmanagement.constants;

public enum ProductType {
    TYPE_A("A Type product"), TYPE_B("B Type product");

    private String productDescription;

    ProductType(String productDescription) {
        this.productDescription = productDescription;
    }
}
