package model.service.RentalService;

import java.time.Duration;

import model.entities.carRental.CarRental;
import model.entities.invoice.Invoice;
import model.service.interfaces.TaxService;

public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;
    private TaxService taxService;
    
    public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
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

    public TaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(TaxService TaxService) {
        this.taxService = TaxService;
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
        double tax = taxService.tax(basicPayment);
        cr.setInvoice(new Invoice(basicPayment, tax));
    }
}
