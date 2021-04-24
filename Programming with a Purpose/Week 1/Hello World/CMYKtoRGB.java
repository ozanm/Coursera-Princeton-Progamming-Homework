public class CMYKtoRGB {
    public static void main(String[] args) {
        double cyan = Double.parseDouble(args[0]);
        double magenta = Double.parseDouble(args[1]);
        double yellow = Double.parseDouble(args[2]);
        double black = Double.parseDouble(args[3]);

        double white = 1 - black;

        System.out.println("red   = " + (int)((255 * white * (1 - cyan)) + 0.5));
        System.out.println("green = " + (int)((255 * white * (1 - magenta)) + 0.5));
        System.out.println("blue  = " + (int)((255 * white * (1 - yellow)) + 0.5));
    }
}
