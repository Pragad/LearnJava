import java.util.concurrent.atomic.AtomicInteger;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class GroupBy {
    public static void main(String[] args) {
        final AtomicInteger counter = new AtomicInteger();

        List<String> entityIds = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        Collection<List<Shape>> recordChunks = entityIds.stream()
                .map(id -> new Shape(id))
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 4))
                .values();

        System.out.println(recordChunks);
    }
}

class Shape {
    public String id;

    public Shape(String id) {
        this.id = id;
    }
}
