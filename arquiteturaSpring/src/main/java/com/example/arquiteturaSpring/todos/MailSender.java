package com.example.arquiteturaSpring.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {
    public void enviarEmail(String Mensagem){
        System.out.println(Mensagem);
    }
}
