package com.system.platform.services;

import java.util.List;
import java.util.UUID;

public interface ICrudService<E, C> {
    E create(C data);
    E readById(UUID id);
    List<E> readAll();
    void deleteById(UUID id);
}
