public class BandMatrix {
    public static void main(String[] args) {
        int width = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        String grid = "";
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
              grid += (Math.abs(i - j) <= n ? "*" : "0") + (j != width - 1 ? "  " : "\n");
            }
        }

        System.out.println(grid);
    }
}
