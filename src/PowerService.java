package ps.csci3901;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * PowerService builds a weighted graph of electricity towers (hubs) and roads between them.
 * It uses Dijkstra's algorithm to compute the shortest path for the repair worker.
 */
public class PowerService {

    private final Map<String, List<Edge>> graph = new HashMap<>();

    public void addTower(String towerId) {
        graph.computeIfAbsent(towerId, ignored -> new ArrayList<>());
    }

    /**
     * Adds an undirected road between two towers with a non-negative distance.
     */
    public void addRoad(String fromTower, String toTower, int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Dijkstra requires non-negative edge weights.");
        }
        addTower(fromTower);
        addTower(toTower);

        graph.get(fromTower).add(new Edge(toTower, distance));
        graph.get(toTower).add(new Edge(fromTower, distance));
    }

    /**
     * Returns the shortest route between startTower and targetTower.
     */
    public PathResult shortestPath(String startTower, String targetTower) {
        if (!graph.containsKey(startTower) || !graph.containsKey(targetTower)) {
            return PathResult.unreachable(startTower, targetTower);
        }

        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String tower : graph.keySet()) {
            distance.put(tower, Integer.MAX_VALUE);
        }
        distance.put(startTower, 0);

        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(NodeDistance::distance));
        pq.offer(new NodeDistance(startTower, 0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            if (!visited.add(current.node())) {
                continue;
            }
            if (current.node().equals(targetTower)) {
                break;
            }

            for (Edge edge : graph.getOrDefault(current.node(), Collections.emptyList())) {
                if (visited.contains(edge.to())) {
                    continue;
                }
                int candidate = distance.get(current.node()) + edge.distance();
                if (candidate < distance.get(edge.to())) {
                    distance.put(edge.to(), candidate);
                    previous.put(edge.to(), current.node());
                    pq.offer(new NodeDistance(edge.to(), candidate));
                }
            }
        }

        if (distance.get(targetTower) == Integer.MAX_VALUE) {
            return PathResult.unreachable(startTower, targetTower);
        }

        List<String> route = new ArrayList<>();
        for (String at = targetTower; at != null; at = previous.get(at)) {
            route.add(at);
        }
        Collections.reverse(route);
        return PathResult.reachable(startTower, targetTower, route, distance.get(targetTower));
    }

    /**
     * Optional helper: determine which damaged tower should be repaired first from a given location.
     */
    public List<HubImpact> prioritizeDamagedTowers(String repairmanTower, List<String> damagedTowers) {
        List<HubImpact> impacts = new ArrayList<>();
        for (String damaged : damagedTowers) {
            PathResult result = shortestPath(repairmanTower, damaged);
            if (result.isReachable()) {
                impacts.add(new HubImpact(damaged, result.getTotalDistance(), result.getPath()));
            }
        }
        impacts.sort(Comparator.comparingInt(HubImpact::distance));
        return impacts;
    }

    public static void main(String[] args) {
        PowerService service = new PowerService();

        // Example graph of towers after hurricane damage.
        service.addRoad("HUB_A", "HUB_B", 5);
        service.addRoad("HUB_A", "HUB_C", 2);
        service.addRoad("HUB_C", "HUB_B", 1);
        service.addRoad("HUB_B", "HUB_D", 3);
        service.addRoad("HUB_C", "HUB_D", 8);
        service.addRoad("HUB_D", "HUB_E", 2);

        PathResult bestRoute = service.shortestPath("HUB_A", "HUB_E");
        if (bestRoute.isReachable()) {
            System.out.println("Shortest path: " + bestRoute.getPath());
            System.out.println("Total distance: " + bestRoute.getTotalDistance());
        } else {
            System.out.println("No route found from " + bestRoute.getStartTower() + " to " + bestRoute.getTargetTower());
        }
    }

    private record Edge(String to, int distance) {}

    private record NodeDistance(String node, int distance) {}

    public static final class PathResult {
        private final String startTower;
        private final String targetTower;
        private final List<String> path;
        private final int totalDistance;
        private final boolean reachable;

        private PathResult(String startTower, String targetTower, List<String> path, int totalDistance, boolean reachable) {
            this.startTower = startTower;
            this.targetTower = targetTower;
            this.path = path;
            this.totalDistance = totalDistance;
            this.reachable = reachable;
        }

        public static PathResult reachable(String startTower, String targetTower, List<String> path, int totalDistance) {
            return new PathResult(startTower, targetTower, List.copyOf(path), totalDistance, true);
        }

        public static PathResult unreachable(String startTower, String targetTower) {
            return new PathResult(startTower, targetTower, List.of(), Integer.MAX_VALUE, false);
        }

        public String getStartTower() {
            return startTower;
        }

        public String getTargetTower() {
            return targetTower;
        }

        public List<String> getPath() {
            return path;
        }

        public int getTotalDistance() {
            return totalDistance;
        }

        public boolean isReachable() {
            return reachable;
        }

        @Override
        public String toString() {
            if (!reachable) {
                return "PathResult{unreachable from " + startTower + " to " + targetTower + "}";
            }
            return "PathResult{" +
                    "startTower='" + startTower + '\'' +
                    ", targetTower='" + targetTower + '\'' +
                    ", path=" + path +
                    ", totalDistance=" + totalDistance +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PathResult that)) return false;
            return totalDistance == that.totalDistance && reachable == that.reachable && Objects.equals(startTower, that.startTower) && Objects.equals(targetTower, that.targetTower) && Objects.equals(path, that.path);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTower, targetTower, path, totalDistance, reachable);
        }
    }
}
