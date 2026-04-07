package HK1;

/**
 * A generic abstract base class for progressions.
 */
public abstract class Progression<T> {
    protected T current;

    public Progression(T start) {
        current = start;
    }

    /** Returns the next value of the progression. */
    public T nextValue() {
        T answer = current;
        advance();
        return answer;
    }

    /** Abstract method to be implemented by subclasses to update 'current'. */
    protected abstract void advance();

    /** Prints the next n values of the progression, separated by commas. */
    public void printProgression(int n) {
        System.out.print(nextValue());
        for (int j = 1; j < n; j++) {
            System.out.print(", " + nextValue());
        }
        System.out.println();
    }
}
