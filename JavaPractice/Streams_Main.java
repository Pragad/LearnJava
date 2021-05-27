import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Point {
    String id;
    String name;
    boolean isArchieved;

    Point(String id, String name, boolean isArchieved) {
        this.id = id;
        this.name = name;
        this.isArchieved = isArchieved;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; Name: " + name + "; IsArchieved: " + isArchieved;
    }
}

class Plan {
    String id;
    Map<String, Point> pointIdToPointMap;

    public Plan(String id, Map<String, Point> myMap) {
        this.id = id;
        this.pointIdToPointMap = myMap;
    }

    public Map<String, Point> getPointIdToPointMap() {
        return pointIdToPointMap;
    }
}

public class Streams_Main {

    private Map<String, Point> pointsMap = new HashMap<>();
    private final Map<String, Plan> planMap = new HashMap<>();
    private Plan myPlan = new Plan("111", pointsMap);

    public void addPoints() {
        Point a1 = new Point("0", "abc", false);
        Point a2 = new Point("1", "drej", false);
        Point a3 = new Point("3", "ojf", true);
        Point a4 = new Point("4", "aefaw", false);
        Point a5 = new Point("5", "aefwa", false);

        pointsMap.put(a1.id, a1);
        pointsMap.put(a2.id, a2);
        pointsMap.put(a3.id, a3);
        pointsMap.put(a4.id, a4);
        pointsMap.put(a5.id, a5);

        planMap.put(myPlan.id, myPlan);
    }

    public void printPoints() {
        /*
        for (Map.Entry<String, Point> p : pointsMap.entrySet()) {
            System.out.println(p.getValue());
        }
        */

        for (Point p : getPointsFromMap()) {
            System.out.println(p);
        }
    }

    public List<Point> getPointsFromMap() {
        String planId = "111";
        return planMap.computeIfAbsent(planId, key -> {
            System.out.println("Absent");
            return null;
        }).getPointIdToPointMap().values().stream().filter(point -> !point.isArchieved).collect(Collectors.toList());
    }

    public boolean removePoint(String id) {
        return true;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Streams");

            Streams_Main obj = new Streams_Main();
            obj.addPoints();
            obj.printPoints();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void foo() {
        final Map<String, Car> existingCars = StorageJsonLdTransformer.getCars(storagesMap)
                .stream()
                .filter(car -> carIds.contains(car.getId()))
                .collect(Collectors.toMap(Car::getId, Function.identity()));

        final Set<String> storageIdsOfCarsToBeDeleted = existingCars.values().stream()
                .map(car -> car.getStorageIdentifier().getStorageId())
                .collect(Collectors.toSet());

        storagesMap = storagesMap.entrySet()
                .stream()
                .filter(entry -> fpIdsOfCarsToBeDeleted.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        spaceIds.stream().collect(Collectors.toMap(Function.identity(), id -> new Exception("Failed to delete")));

        try {
            storageServiceAdapter.call(someValue);
            // TODO: Publish notification for all edits in CHRS Patch Handler.
        } catch (final IOException e) {
            return carIds.stream().collect(Collectors.toMap(Function.identity(), (id) -> new SomeException()));
        }


	    String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
	    List<String> items = Arrays.asList(a, b, c, d);
        Map<String, Exception> map = items.stream().collect(Collectors.toMap(i -> i, i -> new IOException()));
        System.out.println(map);

        // Both are the same

        return entityJsonLd.stream()
                .map(featureNode ->
                        EntityJsonLdTransformer.getEntity(someIdentifier, embedGeometryInFeature(someNode, featureNode)))
                .collect(Collectors.toList());

        return entityJsonLd.stream()
                .map(featureNode -> {
                    try {
                        return EntityJsonLdTransformer.getEntity(someIdentifier, embedGeometryInFeature(someNode, featureNode));
                    } catch (final IOException e) {
                        log.error("error");
                        throw new JsonLdTransformerException("Error happened when transforming entity from JsonLdNode: "
                                + featureNode.getId().getIri(), e);
                    }
                })
                .collect(Collectors.toList());

         final List<Entity> entity = new ArrayList<>();
  
         entityJsonLd.forEach(
                 featureNode -> {
                     try {
                         entity.add(EntityJsonLdTransformer.getEntity(someIdentifier,
                                 embedGeometryInFeature(someNode, featureNode)));
                     } catch (final IOException e) {
                         throw new JsonLdTransformerException("Error happened when transforming entity from JsonLdNode: "
                                 + featureNode.getId().getIri(), e);
                     }
                 }
         );
         return entity;
    }
}


