package com.db.mapper;


import        com.db.bean.Menu;
import        java.util.List;


public interface MenuMapper {

  
    int  insertMenu(Menu menu);
    
    List<Menu> selectMenus(Menu menu);
    
    Menu selectMenu(Menu menu);

}