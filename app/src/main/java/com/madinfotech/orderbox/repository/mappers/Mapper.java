package com.madinfotech.orderbox.repository.mappers;

/**
 * Created by prathameshkesarkar on 21/07/16.
 */
public interface Mapper<K,V> {
    V map(K k);
}
