package com.example.osago;

import static org.junit.jupiter.api.Assertions.*;

class UserViewTest {

    @org.junit.jupiter.api.Test
    void getKefAge() {
        UserView user  = new UserView();
        assertEquals(0.92, user.getKefAge(50,5));
    }

    @org.junit.jupiter.api.Test
    void getPowerKef() {
        UserView user  = new UserView();
        assertEquals(1.1, user.getPowerKef(100));
    }
}