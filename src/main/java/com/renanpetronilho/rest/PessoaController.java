package com.renanpetronilho.rest;

import com.renanpetronilho.hazelcast.Cache;
import com.renanpetronilho.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * Created by renanpetronilho on 14/09/16.
 */
@Controller
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired Cache cache;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Pessoa> getPessoas(){
		return cache.getAll();
	}

	@RequestMapping(path = "/{cpf}" , method = RequestMethod.GET)
	@ResponseBody
	public Pessoa getPessoa(@PathVariable String cpf){
		return cache.get(cpf);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object putPessoa(@RequestBody Pessoa pessoa){
		cache.put(pessoa);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{cpf}")
				.buildAndExpand(pessoa.getCpf()).toUri());
		return new ResponseEntity<>(pessoa, httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/{cpf}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable String cpf){
		cache.delete(cpf);
		return new ResponseEntity<>(HttpEntity.EMPTY,HttpStatus.NO_CONTENT);
	}

}
