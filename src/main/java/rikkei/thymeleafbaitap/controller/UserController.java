package rikkei.thymeleafbaitap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.thymeleafbaitap.dao.UserDao;

import javax.persistence.GeneratedValue;

@Controller
public class UserController
{
@GetMapping("/")
    public String login(){

    return "loginForm";
}
@Autowired
    UserDao userDao;
    @PostMapping("login")
    public String login(@RequestParam(name = "username") String username, @RequestParam (name = "password") String password, Model model)
    {
    if(userDao.isTrueAcc(username,password)){
        return "redirect:/Employee";
    }else {
        return "redirect:/";
    }
    }
}
