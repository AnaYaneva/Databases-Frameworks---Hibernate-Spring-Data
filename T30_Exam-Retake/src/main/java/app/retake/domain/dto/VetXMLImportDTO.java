package app.retake.domain.dto;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vet")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetXMLImportDTO {
    @XmlElement(name = "name")
    @NotNull
    @Size(min=3, max=40)
    private String name;

    @XmlElement(name = "profession")
    @NotNull
    @Size(min=3, max=50)
    private String profession;

    @XmlElement(name = "age")
    @NotNull
    @Min(22)
    @Max(65)
    private Integer age;

    @XmlElement(name = "phone-number")
    @NotNull
    @Pattern(regexp = "(^\\+359[0-9]{9}$)|(^0[0-9]{9}$)")
    private String phoneNumber;

    public VetXMLImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
