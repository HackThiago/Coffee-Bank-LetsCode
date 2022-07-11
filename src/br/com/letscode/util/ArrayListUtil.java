package br.com.letscode.util;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ArrayListUtil {
    public static <T> Collector<T, ?, ArrayList<T>> toArrayList() {
        return Collectors.toCollection(ArrayList::new);
    }
}
