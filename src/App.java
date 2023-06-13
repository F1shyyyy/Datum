import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        LocalDate datumNarozeni = LocalDate.of(2007, 4, 11);
        LocalDate datumZadane = LocalDate.of(1, 1, 1);
        int[] datum = zadaniData("Zadej datum (den, mesic, rok): ");
        boolean valid = false;
        do {
            try {
                datumZadane = LocalDate.of(datum[2], datum[1], datum[0]);
                valid = true;
            } catch (DateTimeException e) {
                datum = zadaniData("Neplatne datum, zadej znovu: ");
            }
        } while (!valid);

        long dny = ChronoUnit.DAYS.between(datumNarozeni, datumZadane);

        if (datumZadane.isAfter(datumNarozeni)) {
            System.out.printf("V zadanem datu jsi %d dni stary.", dny);
        } else if (datumZadane.isBefore(datumNarozeni)) {
            System.out.printf("Jeste jsi se nenarodil, zbyva ti %s dni do tveho narozeni.", -dny);
        } else if (datumZadane.equals(datumNarozeni)) {
            System.out.println("Prave jsi se narodil.");
        }
    }

    private static int[] zadaniData(String zprava) {
        System.out.print(zprava);
        String datum = sc.nextLine();
        sc.nextLine();
        int[] datumArr = zapsaniDataDoPole(datum);
        return datumArr;
    }

    private static int[] zapsaniDataDoPole(String zadaneDatum) {
        int[] datum = new int[0];
        String[] pole = zadaneDatum.split("[. ]+");
        for (String s : pole) {
            datum = Arrays.copyOf(datum, datum.length + 1);
            datum[datum.length - 1] = Integer.parseInt(s);
        }
        return datum;
    }
}