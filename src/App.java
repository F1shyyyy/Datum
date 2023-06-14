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
        System.out.print("Zadej datum (den, mesic, rok): ");
        String datumString = sc.nextLine();
        int[] datum = zapsaniDataDoPole(datumString);
        boolean valid = false;
        do {
            valid = overeniData(datum);
            if (valid) {
                datumZadane = LocalDate.of(datum[2], datum[1], datum[0]);
            } else{
                System.out.print("Neplatne datum, zadej znovu: ");
                datumString = sc.nextLine();
                datum = zapsaniDataDoPole(datumString);
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
    private static boolean overeniData(int[] datum){
        try {
            LocalDate.of(datum[2], datum[1], datum[0]);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
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