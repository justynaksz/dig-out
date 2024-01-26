package com.digout.webapp.service.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper <T, K> {

    public abstract T toModel(K k);

    public abstract K toDTO(T t);


    public List<K> convertModelToDTO(List<T> ts) {
        var dtos = new ArrayList<K>();
        for (T t : ts) {
            dtos.add(toDTO(t));
        }
        return dtos;
    }
}
