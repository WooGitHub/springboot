package com.java.springboot.controller;

import com.java.springboot.bean.User;
import com.java.springboot.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Woo_home
 * @create by 2019/8/12
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/insertPage")
    public String index(){
        return "insertPage";
    }

    @RequestMapping("select/{id}")
    @ResponseBody
    public String save(@PathVariable int id){
        return userService.select(id).toString();
    }

    @RequestMapping("/userList")
    public String userList(Model model){
        List<User> users = userService.userList();
        model.addAttribute("users",users);
        return "userList";
    }

    @RequestMapping("/insert")
    public String save(User user){
        userService.save(user);
        return "redirect:/userList";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/userList";
    }

    @GetMapping("/updatePage/{id}")
    public String updatePage(Model model,@PathVariable int id){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "updatePage";
    }

    @PostMapping("/update")
    public String updateUser(User user){
        userService.update(user);
        System.out.println("修改的用户为 ： " + user.getUserName());
        return "redirect:/userList";
    }
}
