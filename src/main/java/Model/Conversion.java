package Model;

import com.google.gson.annotations.SerializedName;

public class Conversion {
    @SerializedName("base_code") private String baseCode;
    @SerializedName("target_code") private String targetCode;
    @SerializedName("conversion_rate") private String conversionRate;

    public Conversion(String baseCode, String targetCode, String conversionRate) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.conversionRate = conversionRate;
    }


    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(String conversionRate) {
        this.conversionRate = conversionRate;
    }
}
