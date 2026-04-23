package ps.csci3901;

import java.util.List;

/**
 * Represents one damaged hub and the shortest known route to reach it.
 */
public record HubImpact(String hubIdentifier, int distance, List<String> route) {
}
