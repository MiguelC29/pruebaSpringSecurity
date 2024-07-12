package com.felysoft.felysoftApp.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Role {
    ADMINISTRATOR(Arrays.asList(
            Permission.READ_ALL_PRODUCTS,
            Permission.READ_ONE_PRODUCT,
            Permission.CREATE_ONE_PRODUCT,
            Permission.UPDATE_ONE_PRODUCT,
            Permission.DISABLE_ONE_PRODUCT,

            Permission.READ_ALL_CATEGORIES,
            Permission.READ_ONE_CATEGORY,
            Permission.CREATE_ONE_CATEGORY,
            Permission.UPDATE_ONE_CATEGORY,
            Permission.DISABLE_ONE_CATEGORY,

            Permission.READ_MY_PROFILE
    )),
    CUSTOMER(Arrays.asList(
            Permission.READ_ALL_PRODUCTS,
            Permission.READ_MY_PROFILE
    )),
    USER(Arrays.asList(
            Permission.READ_ALL_PRODUCTS,
            Permission.READ_MY_PROFILE
    ));

    private final List<Permission> permissions;
}
