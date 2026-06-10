package com.pysarivka.WeekEnds.security;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pysarivka.WeekEnds.domain.User;

public class CustomUserDetails implements UserDetails {
	@Serial
    private static final long serialVersionUID = 1L; 

    private final User user;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(User user, List<String> roles) {
        this.user = user;
        // Перетворюємо текстові ролі (наприклад, "ROLE_USER") у формат, зрозумілий для Spring Security
        this.authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Повертає захешований пароль з БД
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Оскільки логіном є email, повертаємо його
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Акаунт не протермінований
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Акаунт не заблокований
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Пароль не застарів
    }

    @Override
    public boolean isEnabled() {
        return true; // Акаунт активний
    }

    // Додатковий метод, якщо знадобиться отримати чистий об'єкт користувача
    public User getUser() {
        return user;
    }
}
