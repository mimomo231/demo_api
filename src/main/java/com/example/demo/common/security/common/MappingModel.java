package com.example.demo.common.security.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MappingModel<T, G> {
    T[] source;
    List<G> result;

    public MappingModel(T[] source) {
        this.source = source;
        result = new ArrayList<>();
    }

    public <T, G> List<G> fromArrayToList(T[] a, G rs) {
        return Arrays.stream(a)
                .map(element -> {
                    try {
                         return (G)element.getClass().getMethod("getUsername");
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
