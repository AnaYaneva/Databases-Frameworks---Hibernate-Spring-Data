package app.retake.domain.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class AnimalJSONImportDTO implements Serializable {
    @Expose
    @NotNull
    @Size(min=3, max=20)
    private String name;

    @Expose
    private String type;

    @Expose
    @NotNull
    @Min(1)
    private Integer age;

    @Expose
    @NotNull
    private PassportJSONImportDTO passport;

    public AnimalJSONImportDTO() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return this.passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
