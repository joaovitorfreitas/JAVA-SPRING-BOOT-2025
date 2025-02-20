package com.example.arquiteturaSpring.montadora.api;

import com.example.arquiteturaSpring.montadora.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carros")
public class TesteFabricaController {

    //Quando tem mais de um bean, utilizar Qualifier para diferenciar.
    @Autowired
    //@Qualifier("motorTurbo")
    @Aspirado
    private Motor motor;
    @PostMapping
    public carroStatus ligarCarro(@RequestBody Chave chave){
        var carro = new HondaHRV(motor);
        return carro.darIgnicao(chave);
    }
}
