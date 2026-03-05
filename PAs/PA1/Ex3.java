package PA1;

public class Ex3 {

    public static class DiffProgression extends Progression {
        protected long prev;

        // Default constructor starting with 2 and 200
        public DiffProgression() {
            this(2, 200);
        }

        // Two-argument constructor
        public DiffProgression(long first, long second) {
            super(first);
            // Set 'prev' to first - second so the first call to advance()
            // computes: first - (first - second) = second
            prev = first - second;
        }

        @Override
        protected void advance() {
            long temp = prev;
            prev = current;
            // Removed Math.abs() to allow negative values
            current = current - temp;
        }

        public static void main(String[] args) {
            System.out.print("Default DiffProgression: ");
            Progression p1 = new DiffProgression();
            p1.printProgression(5);

            System.out.print("DiffProgression with 15 and 3: ");
            Progression p2 = new DiffProgression(15, 3);
            p2.printProgression(5);
        }
    }
}
