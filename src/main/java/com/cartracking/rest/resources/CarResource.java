package com.cartracking.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cartracking.main.entities.Car;
import org.springframework.hateoas.ResourceSupport;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarResource extends ResourceSupport {
    private long rid;
    private String number;
    private String brand;
    private String model;
    private UserResource employee;

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UserResource getEmployee() {
        return employee;
    }

    @JsonProperty("employee")
    public void setEmployee(UserResource employee) {
        this.employee = employee;
    }

    public Car toCar() {
        Car car = new Car();
        car.setId(rid);
        car.setBrand(brand);
        car.setModel(model);
        car.setNumber(number);
        if (employee != null) {
            car.setDriver(employee.toUser());
        }

        return car;
    }
}
