package com.spring.ext;



@Compont("compontService")
//@Service("compontService")
public class DefCompontService implements CompontService{

    public void sayHello(String word) {
        System.out.println(word);
    }

}
