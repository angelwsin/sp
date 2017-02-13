package com.spring.factory.method;

import com.def.tag.User;

public class UserFactory {
   
    
        public User getUser(){
            User user = new User();
            user.setEmail("al@gmail.com");
            return user;
        }

}
