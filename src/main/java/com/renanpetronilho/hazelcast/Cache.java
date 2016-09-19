package com.renanpetronilho.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.renanpetronilho.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by renanpetronilho on 14/09/16.
 */
@Component
public class Cache {

	public static final String MAP_PESSOA = "map-pessoa";
	static HazelcastInstance instance;

	@PostConstruct
	public void init() {
		Config cfg = new Config();
		instance = Hazelcast.newHazelcastInstance(cfg);
		instance.getMap(MAP_PESSOA).addEntryListener(new PessoaListener(), true);
	}

	public void put(Pessoa pessoa) {
		Map<Object, Object> mapPessoas = instance.getMap(MAP_PESSOA);
		mapPessoas.put(pessoa.getCpf(), pessoa);
	}

	public void delete(String key) {
		Map<Object, Object> mapPessoas = instance.getMap(MAP_PESSOA);
		mapPessoas.remove(key);
	}

	public Pessoa get(String key) {
		Map<Object, Object> mapPessoas = instance.getMap(MAP_PESSOA);
		return (Pessoa) mapPessoas.get(key);
	}

	public List<Pessoa> getAll() {
		Map<Integer, String> mapPessoas = instance.getMap(MAP_PESSOA);
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for (Object obj : mapPessoas.values())
			pessoas.add((Pessoa) obj);
		return pessoas;
	}
}
