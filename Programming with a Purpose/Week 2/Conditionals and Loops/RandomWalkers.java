public class RandomWalkers {
    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        double totalAmountOfSteps = 0;
        for (int i = 0; i < trials; i++) {
            int x = 0;
            int y = 0;
            int steps = 0;
            while((Math.abs(x) + Math.abs(y)) != r) {
                double delta = Math.random();
                if      (delta <= 0.25) {  x++;  }
                else if (delta <= 0.50) {  x--;  }
                else if (delta <= 0.75) {  y++;  }
                else if (delta <= 1.00) {  y--;  }
                steps++;
            }

            totalAmountOfSteps += steps;
        }

        System.out.println("average number of steps = " + (totalAmountOfSteps / trials));
    }
}
