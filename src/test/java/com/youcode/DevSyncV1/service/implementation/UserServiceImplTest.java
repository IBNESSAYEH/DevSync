package com.youcode.DevSyncV1.service.implementation;

import com.youcode.DevSyncV1.entities.User;
import com.youcode.DevSyncV1.entities.enums.Role;
import com.youcode.DevSyncV1.repository.implementation.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository); // Provide the mock manually
    }

    @Test
    @DisplayName("Should create user with default values if jetonParJour and jetonParMois are null")
    void shouldCreateUserWithDefaultValues() {
        User user = new User();
        user.setJetonParJour(null);
        user.setJetonParMois(null);

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertThat(createdUser.getJetonParJour()).isEqualTo("2");
        assertThat(createdUser.getJetonParMois()).isEqualTo("1");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should update user jeton remplacement")
    void shouldUpdateUserJetonRemplacement() {
        when(userRepository.updateUserJetonRemplacement(1L)).thenReturn(true);

        boolean result = userService.updateUserJetonRemplacement(1L);

        assertThat(result).isTrue();
        verify(userRepository, times(1)).updateUserJetonRemplacement(1L);
    }

    @Test
    @DisplayName("Should return user by ID")
    void shouldReturnUserById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(user);

        User foundUser = userService.getUserById(1L);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(1L);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should return all users")
    void shouldReturnAllUsers() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertThat(allUsers).hasSize(2);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should delete user by ID")
    void shouldDeleteUserById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(user);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    @DisplayName("Should return user if email and password match")
    void shouldReturnUserIfEmailAndPasswordMatch() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.findByEmailAndPassword("test@example.com", "password")).thenReturn(user);

        User loggedInUser = userService.login("test@example.com", "password");

        assertThat(loggedInUser).isNotNull();
        assertThat(loggedInUser.getEmail()).isEqualTo("test@example.com");
        verify(userRepository, times(1)).findByEmailAndPassword("test@example.com", "password");
    }

    @Test
    void addUser_ShouldThrowIllegalArgumentException_WhenUserIsNull() {
        User user = null;

        ValidatObjectExeption thrown = assertThrows(ValidatObjectExeption.class, () -> {
            userService.createUser(user);
        });

        assertEquals("User cannot be null", thrown.getMessage());
    }

    @Test
    void addUser_ShouldThrowValidateObjectExeption_WhenUserIsEmpty() {
        User emptyUser = new User();

        ValidatObjectExeption thrown = assertThrows(ValidatObjectExeption.class, () -> {
            userService.createUser(emptyUser);
        });

        assertEquals("User cannot be null", thrown.getMessage());
    }

    @Test
    void addUser_ShouldThrowValidateObjectExeption_WhenUserIsExiste() {
        User existingUser  = new User("zakaria", "password", "Zakaria", "Benlamlih", "zakaria@example.com", Role.USER );
        User newUser  = new User("zakaria", "password", "Zakaria", "Benlamlih", "zakaria@example.com", Role.USER);

        when(userRepository.findByEmail(newUser.getEmail())).thenReturn(existingUser);

        ValidatObjectExeption thrown = assertThrows(ValidatObjectExeption.class, () ->{
            userService.createUser(newUser);
        });

        assertEquals("User with email already exists", thrown.getMessage());

    }
}