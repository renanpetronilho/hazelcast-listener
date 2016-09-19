package com.renanpetronilho.hazelcast;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.MapEvent;
import com.hazelcast.map.listener.*;
import com.renanpetronilho.model.Pessoa;

/**
 * Created by renanpetronilho on 14/09/16.
 */
public class PessoaListener implements EntryAddedListener<String, Pessoa>, EntryRemovedListener<String, Pessoa>,
		EntryUpdatedListener<String, Pessoa>, MapClearedListener {

	@Override
	public void entryAdded(EntryEvent<String, Pessoa> entryEvent) {
		System.out.println("Pessoa Adicionada " + entryEvent);
		System.out.println(entryEvent.getValue());
	}

	@Override
	public void entryRemoved(EntryEvent<String, Pessoa> entryEvent) {
		System.out.println("Pessoa Removida( " + entryEvent);
		System.out.println(entryEvent.getOldValue());
	}

	@Override
	public void entryUpdated(EntryEvent<String, Pessoa> entryEvent) {
		System.out.println("Pessoa Atualizada " + entryEvent);
	}


	@Override
	public void mapCleared(MapEvent mapEvent) {
	}
}
