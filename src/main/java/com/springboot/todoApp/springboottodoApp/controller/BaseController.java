package com.springboot.todoApp.springboottodoApp.controller;

import com.springboot.todoApp.springboottodoApp.security.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    public AppUser getCurrentUser(){

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal(); // user model;

        return  appUser;
    }
}
