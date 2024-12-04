import java.util.Map;

public class RespostaAPI {
    private String base; // Moeda base
    private Map<String, Double> conversion_rates; // Taxas de conversão

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversion_rates = conversionRates;
    }
}
