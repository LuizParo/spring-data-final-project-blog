package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExceptionHandler(Exception.class)
    public ModelAndView genericException(HttpServletRequest request, Exception exception) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("mensagem", "Ocorreu um erro durante a operação, tente noavmente!");
        view.addObject("url", request.getRequestURL());
        view.addObject("exception", exception);
        
        return view;
    }
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView maxUploadSizeExceededException(HttpServletRequest request, MaxUploadSizeExceededException exception) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("mensagem", "O arquivo selecionado não pode ser maior do que 100kb!");
        view.addObject("url", request.getRequestURL());
        view.addObject("exception", exception);
        
        return view;
    }
}