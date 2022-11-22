package com.panjin.caching.redis;

import org.springframework.data.repository.CrudRepository;

/**
 * @author panjin
 */
public interface ItemRepository extends CrudRepository<Item, String> {
}
