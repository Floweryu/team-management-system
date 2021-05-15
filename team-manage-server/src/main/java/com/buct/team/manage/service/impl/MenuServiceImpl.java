package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.MenuMapper;
import com.buct.team.manage.entity.Menu;
import com.buct.team.manage.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/9 15:53
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<Menu> getMenuList() {
        List<Menu> menus = menuMapper.getMenuList();

        return buildMenuTree(menus);
    }

    @Override
    public List<Menu> getMenuByRole(String role) {
        List<Menu> menus = menuMapper.getMenuByRole(role);
        return buildMenuTree(menus);
    }

    private List<Menu> buildMenuTree(List<Menu> menuList) {
        List<Menu> menuTree = new ArrayList<>();

        for (Menu menu : menuList) {
            if (menu.getParentId().equals(0L)) {
                Menu newMenu = buildChildTree(menu, menuList);
                menuTree.add(newMenu);
            }
        }
        return menuTree;
    }

    private Menu buildChildTree(Menu parent, List<Menu> menuList) {
        List<Menu> childMenus =new  ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId().equals(parent.getId())) {
                childMenus.add(buildChildTree(menu, menuList));
            }
        }
        parent.setChildMenu(childMenus);
        return parent;
    }
}
