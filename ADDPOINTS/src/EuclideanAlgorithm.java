public class EuclideanAlgorithm {
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a; // НОД(a, 0) = a
        } else if (a == 0) {
            return b; // НОД(0, b) = b
        } else if (a < b) {
            return gcd(b, a); // НОД(a, b) = НОД(b, a), если a < b
        } else {
            return gcd(b, a % b); // НОД(a, b) = НОД(b, a % b), если b != 0
        }
    }

    public static void main(String[] args) {
        int a = 48;
        int b = 18;

        int result = gcd(a, b);

        System.out.println("Наибольший общий делитель: " + result);
    }
}