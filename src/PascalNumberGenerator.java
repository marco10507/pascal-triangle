import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class PascalNumberGenerator {
    private final static Map<Coordinate, BigInteger> pascalNumberMap = new HashMap<>();

    /**
     * using recursion
     */
    BigInteger generateRecursively(final long row, final long column) {
        if (column == 0 || (column == row)) {
            return new BigInteger("1");
        }

        return generateRecursively(row - 1, column).add(generateRecursively(row - 1, column - 1));
    }

    /**
     * Optimizing method generateRecursively by storing already seen results (dynamic programming)
     */
    BigInteger generateDynamically(final long row, final long column) {
        if (column == 0 || (column == row)) {
            return new BigInteger("1");
        }

        Coordinate pascalNumberCoordinate = new Coordinate(row, column);
        if (pascalNumberMap.containsKey(pascalNumberCoordinate)) {
            return pascalNumberMap.get(pascalNumberCoordinate);
        }

        Coordinate pascalNumber1Coordinate = new Coordinate(row - 1, column);
        final BigInteger pascalNumber1 = generateDynamically(pascalNumber1Coordinate.row, pascalNumber1Coordinate.column);
        storePascalNumber(pascalNumber1Coordinate, pascalNumber1);

        Coordinate pascalNumber2Coordinate = new Coordinate(row - 1, column - 1);
        final BigInteger pascalNumber2 = generateDynamically(pascalNumber2Coordinate.row, pascalNumber2Coordinate.column);
        storePascalNumber(pascalNumber2Coordinate, pascalNumber2);

        final BigInteger pascalNumber = pascalNumber1.add(pascalNumber2);
        storePascalNumber(pascalNumberCoordinate, pascalNumber);

        return pascalNumber;
    }

    private void storePascalNumber(Coordinate coordinate, final BigInteger pascalNumber) {
        if (!pascalNumberMap.containsKey(coordinate)) {
            pascalNumberMap.put(coordinate, pascalNumber);
        }
    }

    private static class Coordinate {
        private final long row;
        private final long column;

        Coordinate(long row, long column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return this.row == that.row && this.column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
