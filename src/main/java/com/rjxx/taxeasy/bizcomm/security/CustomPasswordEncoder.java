package com.rjxx.taxeasy.bizcomm.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rjxx.utils.PasswordUtils;

/**
 * Created by admin on 2016/3/1.
 */
@Service("passwordEncoder")
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordUtils.encrypt((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(PasswordUtils.encrypt((String) rawPassword));
    }
}
