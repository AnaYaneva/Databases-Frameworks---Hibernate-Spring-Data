package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PassportJSONImportDTO implements Serializable {
    @Expose
    @Pattern(regexp = "^[A-Za-z]{7}[0-9]{3}$")
    private String serialNumber;

    @Expose
    @NotNull
    @Size(min=3, max=30)
    private String ownerName;

    @Expose
    @NotNull
    @Pattern(regexp = "(^\\+359[0-9]{9}$)|(^0[0-9]{9}$)")
    private String ownerPhoneNumber;

    @Expose
    @NotNull
    private String registrationDate;

    public PassportJSONImportDTO() {
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return this.ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

}
