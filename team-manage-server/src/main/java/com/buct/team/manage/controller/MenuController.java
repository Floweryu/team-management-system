package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.entity.Menu;
import com.buct.team.manage.enums.RoleEnum;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Log(value = "获取所有菜单")
    @GetMapping("/menus")
    public Result<List<Menu>> getMenus(@RequestParam String role) {
        if (role.isEmpty()) {
            return Result.error(400, "role can not be null");
        }
        if (! role.equals(RoleEnum.ADMIN.getState()) && ! role.equals(RoleEnum.COMMON.getState())) {
            return Result.error(400, "role must be 'admin' or 'common'");
        }
        try {
            List<Menu> menuList = menuService.getMenuByRole(role);
            log.info("获取的菜单： {}", menuList);
            return Result.success(menuList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
