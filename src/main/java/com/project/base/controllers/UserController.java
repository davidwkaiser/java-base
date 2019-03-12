package com.project.base.controllers;

import com.project.base.objects.UserDto;
//import org.springframework.security.core.userdetails.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @RequestMapping(value="/user/registration", method= RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

//    public ModelAndView registerUserAccount(
//            @ModelAttribute("user") @Valid UserDto accountDto,
//            BindingResult result,
//            WebRequest request,
//            Errors errors) {
//
//        return null;
//    }


    @RequestMapping(value="/user/registration", method=RequestMethod.POST)
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid UserDto accountDto,
             BindingResult result,
             WebRequest request,
             Errors errors){
        SecurityProperties.User registered = new SecurityProperties.User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }


    }

    private SecurityProperties.User createUserAccount(UserDto accountDto, BindingResult result) {
        SecurityProperties.User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }



}
