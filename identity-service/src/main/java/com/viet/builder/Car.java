package com.viet.builder;

public class Car {

    // Các thuộc tính của Car
    private String bodyStyle;
    private String power;
    private String engine;
    private String breaks;
    private String seats;
    private String windows;
    private String fuelType;
    private String carType;

    // Constructor của Car là private để ngăn việc tạo đối tượng trực tiếp
    private Car(CarBuilder builder) {
        this.bodyStyle = builder.bodyStyle;
        this.power = builder.power;
        this.engine = builder.engine;
        this.breaks = builder.breaks;
        this.seats = builder.seats;
        this.windows = builder.windows;
        this.fuelType = builder.fuelType;
        this.carType = builder.carType;
    }

    // Getter cho các thuộc tính (không bắt buộc nhưng thường dùng)
    public String getBodyStyle() {
        return bodyStyle;
    }

    public String getPower() {
        return power;
    }

    public String getEngine() {
        return engine;
    }

    public String getBreaks() {
        return breaks;
    }

    public String getSeats() {
        return seats;
    }

    public String getWindows() {
        return windows;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getCarType() {
        return carType;
    }

    @Override
    public String toString() {
        return "Car [bodyStyle=" + bodyStyle + ", power=" + power + ", engine=" + engine + ", breaks="
                + breaks + ", seats=" + seats + ", windows=" + windows + ", fuelType="
                + fuelType + ", carType=" + carType + "]";
    }

    // Lớp Builder bên trong Car
    public static class CarBuilder {
        private String bodyStyle;
        private String power;
        private String engine;
        private String breaks;
        private String seats;
        private String windows;
        private String fuelType;
        private String carType;

        // Constructor của CarBuilder yêu cầu carType
        public CarBuilder(String carType) {
            this.carType = carType;
        }

        public CarBuilder setBodyStyle(String bodyStyle) {
            this.bodyStyle = bodyStyle;
            return this;
        }

        public CarBuilder setPower(String power) {
            this.power = power;
            return this;
        }

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setBreaks(String breaks) {
            this.breaks = breaks;
            return this;
        }

        public CarBuilder setSeats(String seats) {
            this.seats = seats;
            return this;
        }

        public CarBuilder setWindows(String windows) {
            this.windows = windows;
            return this;
        }

        public CarBuilder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
