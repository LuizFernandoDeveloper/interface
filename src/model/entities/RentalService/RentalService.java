package model.entities.RentalService;

import java.time.Duration;

import model.entities.BrazilTaxService.BrazilTaxService;
import model.entities.carRental.CarRental;
import model.entities.invoice.Invoice;

public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;
    private BrazilTaxService brazilTaxService;
    
    public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService brazilTaxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.brazilTaxService = brazilTaxService;
    }

    public Double getpricePerHour() {
        return pricePerHour;
    }

    public void setpricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Double getpricePerDay() {
        return pricePerDay;
    }

    public void setpricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BrazilTaxService getBrazilTaxService() {
        return brazilTaxService;
    }

    public void setBrazilTaxService(BrazilTaxService brazilTaxService) {
        this.brazilTaxService = brazilTaxService;
    }

    
    public void processInvoice(CarRental cr ){
        double minutes = Duration.between(cr.getStart(), cr.getFinish()).toMinutes();
        double hours = minutes / 60.00;

        double basicPayment = 0;

        if(hours <= 12.0){
            basicPayment = pricePerHour * Math.ceil(hours);
        }else{
            basicPayment = pricePerDay * Math.ceil(hours / 24.0);
        }
        double tax = brazilTaxService.tax(basicPayment);
        cr.setInvoice(new Invoice(basicPayment, tax));
    }
}
