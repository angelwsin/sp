package com.tx;

import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements UserService {

    @Transactional
    public void getUser() {
    }

}
