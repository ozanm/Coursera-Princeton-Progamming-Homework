public class RightTriangle {
    public static void main(String[] args) {
        int a = (int)Math.pow(Integer.parseInt(args[0]), 2);
        int b = (int)Math.pow(Integer.parseInt(args[1]), 2);
        int c = (int)Math.pow(Integer.parseInt(args[2]), 2);

        System.out.println(a + b == c || a + c == b || b + c == a);
    }
}
