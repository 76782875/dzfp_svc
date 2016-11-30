package com.rjxx.taxeasy.bizcomm.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 2016/3/1.
 */
public class SecurityUser implements UserDetails {

    private WebPrincipal webPrincipal;

    private List<SimpleGrantedAuthority> authorities;

    public SecurityUser(WebPrincipal webPrincipal, List<SimpleGrantedAuthority> authorities) {
        this.webPrincipal = webPrincipal;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return webPrincipal.getPassword();
    }

    @Override
    public String getUsername() {
        return webPrincipal.getDlyhid();
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public WebPrincipal getWebPrincipal() {
        return webPrincipal;
    }

    public void setWebPrincipal(WebPrincipal webPrincipal) {
        this.webPrincipal = webPrincipal;
    }
}
