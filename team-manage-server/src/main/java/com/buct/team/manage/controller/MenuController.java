package com.buct.team.manage.controller;

import com.buct.team.manage.entity.Menu;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/9 16:07
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menus")
    public Result<List<Menu>> getMenus() {
        try {
            List<Menu> menuList = menuService.getMenuList();
            log.info("获取的菜单： {}", menuList);
            return Result.success(menuList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
