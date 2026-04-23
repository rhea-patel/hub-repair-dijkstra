package ps.csci3901;

/**
 * Simple model to describe a postal code currently affected by hub damage.
 */
public record DamagedPostalCodes(String postalCode, int populationWithoutPower) {
}
