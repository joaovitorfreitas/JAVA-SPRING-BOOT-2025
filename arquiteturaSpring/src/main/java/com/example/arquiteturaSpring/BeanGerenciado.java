package com.example.arquiteturaSpring;

import com.example.arquiteturaSpring.todos.TodoEntity;
import com.example.arquiteturaSpring.todos.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


//Escopo padrão dos beans é sigleton.Instanacia uníca a toda aplicação
//Request - só existe durante requisição, em uma pagina web
//session, enquanto a sessão do usuário durar
//application se extende a todos usuários, serve só pra application web; como se fosse session mas de todos usuários



//Lazy instancia só quando necessário
@Lazy
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
//@Scope(WebApplicationContext.SCOPE_REQUEST)
//@Scope("request")
//@Scope("session")
//@Scope("application")
public class BeanGerenciado {
    //Tipos do injeção


    //Injeção numero, via propriedade
    @Autowired
    private TodoValidator validator;

    //Via construtor
    public BeanGerenciado(TodoValidator validator) {
        this.validator = validator;
    }

    public void utilizar(){
        var todo = new TodoEntity();
        validator.validar(todo);
    }


    //Por setter, opcional, pode alterar em uma lógica
    @Autowired
    public void setValidator(TodoValidator validator){
        this.validator = validator;
    }

}
