package br.com.jjflix.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jjflix.client.ClientAppMc;
import br.com.jjflix.domain.Cliente;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
    
    @Autowired
    private ClientAppMc clientAppMc;
    
    
    @GetMapping
    public Cliente[] consumindoPessoa() {
	return clientAppMc.getApiClients();
    }
}
