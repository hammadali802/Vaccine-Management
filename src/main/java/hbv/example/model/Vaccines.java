package hbv.example.model;

public class Vaccines {


        private int id;
        private int vaccinationCenterId;
        private String name;
        private String manufacturer;
        private int quantity;
        private VaccinationCenter vaccinationCenter;

        public Vaccines() {
        }

        public Vaccines(int id, int vaccinationCenterId, String name, String manufacturer, int quantity) {
            this.id = id;
            this.vaccinationCenterId = vaccinationCenterId;
            this.name = name;
            this.manufacturer = manufacturer;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVaccinationCenterId() {
            return vaccinationCenterId;
        }

        public void setVaccinationCenterId(int vaccinationCenterId) {
            this.vaccinationCenterId = vaccinationCenterId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public VaccinationCenter getVaccinationCenter() {
            return vaccinationCenter;
        }

        public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
            this.vaccinationCenter = vaccinationCenter;
        }

        // Additional methods specific to Vaccine functionality (optional)
    }


