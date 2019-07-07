package br.com.fiap.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.fiap.entity.relationship.Item;

public interface ItemRepository  extends CrudRepository<Item, Long>{

}
