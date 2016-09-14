package com.renanpetronilho.hazelcast;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.MapEvent;
import com.hazelcast.map.listener.*;
import org.springframework.stereotype.Component;

/**
 * Created by renanpetronilho on 14/09/16.
 */
public class PessoaListener implements EntryAddedListener<String, String>, EntryRemovedListener<String, String>,
		EntryUpdatedListener<String, String>, EntryEvictedListener<String, String>, MapEvictedListener,
		MapClearedListener {

	@Override
	public void entryAdded(EntryEvent<String, String> entryEvent) {
		System.out.println("Pessoa Adicionada " + entryEvent);
	}

	@Override
	public void entryRemoved(EntryEvent<String, String> entryEvent) {
		System.out.println("Pessoa Removida( " + entryEvent);
	}

	@Override
	public void entryUpdated(EntryEvent<String, String> entryEvent) {
		System.out.println("Pessoa Atualizada " + entryEvent);
	}

	@Override
	public void entryEvicted(EntryEvent<String, String> entryEvent) {

	}

	@Override
	public void mapCleared(MapEvent mapEvent) {

	}

	@Override
	public void mapEvicted(MapEvent mapEvent) {

	}
}
