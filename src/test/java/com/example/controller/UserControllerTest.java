package com.example.controller;

import com.example.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    private List<User> userList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(System.currentTimeMillis());
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        //userList.add(user1);

    }

    @Test
    public void testListUsers() {
        when(model.addAttribute("users", userList)).thenReturn(model);

        String viewName = userController.listUsers(model);

        assertEquals("user/list", viewName);
        verify(model, times(1)).addAttribute("users", userList);
    }

    // Ajoutez d'autres tests pour les autres méthodes du UserController
}
