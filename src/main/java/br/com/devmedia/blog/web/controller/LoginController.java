package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "auth")
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) boolean error,
                              @RequestParam(value = "logout", required = false) boolean logout,
                              ModelMap model) {
        if(error) {
            model.addAttribute("error", "Usuário e senha não conferem!");
            return new ModelAndView("login", model);
        }
        if(logout) {
            model.addAttribute("logout", "Usuário saiu do sistema com sucesso!");
            return new ModelAndView("login", model);
        }
        return new ModelAndView("redirect:/");
    }
    
    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public ModelAndView acessoNegado() {
        return new ModelAndView("error", "mensagem", "Acesso negado! Área restrita!");
    }
}