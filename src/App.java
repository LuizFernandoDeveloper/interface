import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.BrazilTaxService.BrazilTaxService;
import model.entities.RentalService.RentalService;
import model.entities.carRental.CarRental;
import model.entities.vehicle.Vehicle;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Enter com os dados aluguel");

        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();

        System.out.print("Retirada (dd/MM/yyyy hh:mm)");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);

        System.out.print("Retorno (dd/MM/yyyy hh:mm)");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental carRental  =  new CarRental(start, finish, new Vehicle(carModel) );

        System.out.print("Enter  com preço por hora: ");
        double pricePerHour = sc.nextDouble();

        System.out.print("Enter com preço por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        rentalService.processInvoice(carRental);

        System.out.println("Fatura: ");
        System.out.println("Pagamento basico: " + String.format("%.2f",carRental.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + String.format("%.2f", carRental.getInvoice().getTax()));
        System.out.println("Pagemento totala: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));

        sc.close();
    }
}
