package com.pyramid.tech.domain.registration.service.imp;

import com.pyramid.tech.domain.registration.model.AppUser;
import com.pyramid.tech.domain.registration.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder encoder;

    private AppUser user;
    private Long userId;


    @BeforeEach
    void setUp() {
        userId = 1L;
        user = new AppUser();
        user.setId(userId);
        user.setUsername("Muna solo");
        user.setPassword("muna@muna.com");
        user.setEnabled(Boolean.TRUE);


    }

    @Test
    void shouldCreateUser() {

        when(userRepository.save(any(AppUser.class))).thenReturn(user);

        final var underTest = userService.save(user);

        assertNotNull(user.getUsername());
        assertEquals(user, underTest);
        verify(userRepository, times(1)).save(any(AppUser.class));
    }

    @Test
    void findAll() {
    }

    @Test
    void selectUser() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        final var underTest = userService.selectUser(userId);

        assertEquals(user, underTest);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldThrowsExceptionSelectUserByIdNotFound() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.selectUser(userId));

        verify(userRepository, times(1)).findById(anyLong());

    }

    @Test
    void selectUserByUsername() {
    }

    @Test
    void delete() {
    }
}