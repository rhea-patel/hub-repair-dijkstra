# Hurricane Power Repair Planner (Dijkstra)

This project provides a Java implementation of **Dijkstra's algorithm** to plan the shortest route for a repair technician to reach damaged electricity hubs after a hurricane.

## What is implemented

- `PowerService` builds a weighted graph of hubs/towers and roads.
- `shortestPath(startHub, targetHub)` returns:
  - the shortest path (ordered list of hub IDs), and
  - the total travel distance.
- `prioritizeDamagedTowers(repairmanHub, damagedHubs)` sorts damaged hubs by nearest-first repair order.
- `HubImpact` and `DamagedPostalCodes` are simple domain records.

## Project structure

- `src/PowerService.java` – Core graph + Dijkstra logic and demo `main` method.
- `src/HubImpact.java` – Repair-priority output model.
- `src/DamagedPostalCodes.java` – Damaged postal code model.
- `src/powerservice.sql` – Original database schema draft.

## Compile and run

From the repository root:

```bash
javac -d out src/*.java
java -cp out ps.csci3901.PowerService
```

Expected output (example):

```text
Shortest path: [HUB_A, HUB_C, HUB_B, HUB_D, HUB_E]
Total distance: 8
```

## How to use in your own scenario

1. Create a `PowerService` object.
2. Add all hubs with `addTower(...)` (optional if roads already cover all hubs).
3. Add roads with non-negative distances using `addRoad(from, to, distance)`.
4. Call `shortestPath(currentRepairmanHub, damagedHub)`.
5. If you have multiple damaged hubs, call `prioritizeDamagedTowers(...)`.

### Example (inside Java code)

```java
PowerService service = new PowerService();
service.addRoad("HUB_A", "HUB_B", 5);
service.addRoad("HUB_A", "HUB_C", 2);
service.addRoad("HUB_C", "HUB_B", 1);

PowerService.PathResult result = service.shortestPath("HUB_A", "HUB_B");
System.out.println(result.getPath());          // [HUB_A, HUB_C, HUB_B]
System.out.println(result.getTotalDistance()); // 3
```

## Notes

- Dijkstra requires **non-negative** edge weights.
- Current implementation is in-memory and not yet directly connected to MySQL queries.
- You can extend this by loading roads/hubs from your `DistributionHub` and damage tables.
