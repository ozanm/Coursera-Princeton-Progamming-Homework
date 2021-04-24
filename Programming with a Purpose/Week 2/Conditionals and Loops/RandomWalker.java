public class RandomWalker {
    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]);

        int x = 0;
        int y = 0;
        int steps = -1;
        while((Math.abs(x) + Math.abs(y)) <= r) {
            System.out.println("(" + x + ", " + y + ")");
            double delta = Math.random();
            if      (delta <= 0.25) {  x++;  }
            else if (delta <= 0.50) {  x--;  }
            else if (delta <= 0.75) {  y++;  }
            else if (delta <= 1.00) {  y--;  }
            steps++;
        }

        System.out.println("steps = " + steps);
    }
}
