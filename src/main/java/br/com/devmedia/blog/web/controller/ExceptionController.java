package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

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
    
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(HttpServletRequest request, NoHandlerFoundException exception) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("mensagem", "Ops :( <br/> Está página não existe por aqui!");
        view.addObject("url", request.getRequestURL());
        view.addObject("exception", exception);
        
        return view;
    }
}