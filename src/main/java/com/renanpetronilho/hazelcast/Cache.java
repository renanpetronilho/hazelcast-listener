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
		Map<Object, Object> mapCustomers = instance.getMap(MAP_PESSOA);
		mapCustomers.put(pessoa.getCpf(), pessoa);
	}

	public void delete(String key) {
		Map<Object, Object> mapCustomers = instance.getMap(MAP_PESSOA);
		mapCustomers.remove(key);
	}

	public Pessoa get(String key) {
		Map<Object, Object> mapCustomers = instance.getMap(MAP_PESSOA);
		return (Pessoa) mapCustomers.get(key);
	}

	public List<Pessoa> getAll() {
		Map<Integer, String> mapCustomers = instance.getMap(MAP_PESSOA);
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for (Object obj : mapCustomers.values())
			pessoas.add((Pessoa) obj);
		return pessoas;
	}
}
