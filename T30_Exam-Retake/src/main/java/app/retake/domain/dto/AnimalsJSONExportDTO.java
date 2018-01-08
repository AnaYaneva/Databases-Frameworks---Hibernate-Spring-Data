package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnimalsJSONExportDTO implements Serializable {
    @Expose
    private String ownerName;
    @Expose
    private String animalName;
    @Expose
    private Integer age;
    @Expose
    private String serialNumber;
    @Expose
    private String registeredOn;

    public AnimalsJSONExportDTO() {
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAnimalName() {
        return this.animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRegisteredOn() {
        return this.registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }


}
