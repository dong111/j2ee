package cn.dong111.web.controller;

/**
 * Created by LittleXuan on 2015/10/18.
 */

import cn.dong111.baseBus.entity.User;
import cn.dong111.baseBus.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/selectAllUser",method = RequestMethod.GET)
    public String getAllUsers(){
        return "userList";
    }




    @RequestMapping(value="/userInfo/{id}", method= RequestMethod.GET)
    public String toIndex(HttpServletRequest request, Model model, @PathVariable("id") String id) {
        if(StringUtils.isEmpty(id)){
            throw new IllegalArgumentException("id不能为空");
        }
        int userId = Integer.parseInt(id);
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user";
    }
}