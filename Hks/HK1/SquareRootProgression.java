package HK1;

/**
 * A progression where each value is the square root of the previous value.
 */
public class SquareRootProgression extends Progression<Double> {

    /** Default constructor starts with 65,536.0 as specified in 2.7.24. */
    public SquareRootProgression() {
        this(65536.0);
    }

    /** Parametric constructor starts with a specified Double. */
    public SquareRootProgression(Double first) {
        super(first);
    }

    /** Computes the square root of the current value. */
    @Override
    protected void advance() {
        current = Math.sqrt(current);
    }

    /** Main method to test the implementation. */
    public static void main(String[] args) {
        System.out.println("Testing Default SquareRootProgression:");
        SquareRootProgression p1 = new SquareRootProgression();
        p1.printProgression(5);

        System.out.println("\nTesting Custom SquareRootProgression (Start: 16.0):");
        SquareRootProgression p2 = new SquareRootProgression(16.0);
        p2.printProgression(4);
    }
}
