public class PascalApp {
    public static void main(String[] args) {
        PascalNumberGenerator pascalNumberGenerator = new PascalNumberGenerator();

        for (long row = 0; row <= 50; row++) {
            for (long column = 0; column <= row; column++) {
                System.out.printf("%d \t", pascalNumberGenerator.generateDynamically(row, column));
            }

            System.out.println("");
        }
    }
}
