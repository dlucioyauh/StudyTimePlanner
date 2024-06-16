import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class StudyPlanner {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pergunta ao usuário quando começou o bootcamp
        System.out.print("Quando você começou o bootcamp (formato: dd/MM/yyyy): ");
        String startDateStr = scanner.next();

        // Converter a string de data para LocalDate
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Pergunta ao usuário a data final do curso
        System.out.print("Qual é a data final do curso (formato: dd/MM/yyyy): ");
        String endDateStr = scanner.next();

        // Converter a string de data final para LocalDate
        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Pergunta ao usuário quantas horas de curso são no total
        System.out.print("Quantas horas de curso são no total: ");
        int totalHours = scanner.nextInt();

        // Calcular o número total de dias entre a data de início e a data final
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate);

        // Calcular quantas horas por dia você precisa estudar
        double hoursPerDay = (double) totalHours / totalDays;

        // Calcular quantas horas de estudo são nos dias de semana (segunda a sexta-feira)
        long weekdaysCount = 0;
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                weekdaysCount++;
            }
            date = date.plusDays(1);
        }

        // Pergunta ao usuário quantas horas pode estudar no sábado e no domingo
        System.out.print("Quantas horas você pode estudar no sábado: ");
        int hoursSaturday = scanner.nextInt();
        System.out.print("Quantas horas você pode estudar no domingo: ");
        int hoursSunday = scanner.nextInt();

        // Subtrair as horas de estudo no sábado e domingo dos dias úteis
        weekdaysCount -= (hoursSaturday > 0 ? 1 : 0); // Se houver horas no sábado, subtrai um dia útil
        weekdaysCount -= (hoursSunday > 0 ? 1 : 0);   // Se houver horas no domingo, subtrai um dia útil

        // Calcular quantas horas por dia você precisa estudar nos dias de semana
        double hoursPerWeekday = (double) totalHours / weekdaysCount;

        // Exibir o resultado formatado
        System.out.println("\nVocê precisa estudar aproximadamente " + String.format("%.2f", hoursPerDay) +
                " horas por dia para terminar o bootcamp até " + endDate);
        System.out.println("Isso equivale a aproximadamente " + String.format("%.2f", hoursPerWeekday) +
                " horas por dia durante os dias de semana.");
        System.out.println("Você pode estudar " + hoursSaturday + " horas no sábado e " + hoursSunday +
                " horas no domingo.");

        // Fechar o scanner.
        scanner.close();
    }
}
