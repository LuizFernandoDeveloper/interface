import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre os dados do contrato:");
        System.out.print("Numero: ");
        int number = sc.nextInt();
        System.out.print("Data(dd/MM/yyyy): ");
        LocalDate date  = LocalDate.parse(sc.next(), fmt);
        System.out.print("Valor do contrato: ");
        Double contractValue = sc.nextDouble();

        Contract obj = new Contract(number, date, contractValue);

        System.out.print("Enter com numero  de parcelas");
        Integer numberOfInstallments = sc.nextInt();
        sc.nextLine();

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(obj, numberOfInstallments);

        for(Installment installment: obj.getInstallments()){
            System.out.println(installment);
        }
        
        sc.close();
    }
}
