package br.com.jjflix.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jjflix.domain.Cliente;


@Service
public class ClientAppMc {
    
    private static final String URL = "https://application-mc.herokuapp.com/clientes";
    
    public Cliente[] getApiClients() {

	RestTemplate restTemplate = new RestTemplate();
	
	Cliente[] clientes = restTemplate.getForEntity(URL, Cliente[].class).getBody();
	
	return clientes;
    }
}
