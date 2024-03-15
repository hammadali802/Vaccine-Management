package hbv.example.model;

import java.util.ArrayList;
import java.util.List;


    public class VaccinationCenter {

        private int id;
        private String name;
        private String location;

        // List to manage timeslots offered by this center
        private List<Timeslot> timeslots;

        // List to manage vaccines offered by this center
        private List<Vaccines> vaccines;

        public VaccinationCenter() {
        }

        public VaccinationCenter(int id, String name, String location) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.timeslots = new ArrayList<>();  // Initialize empty lists
            this.vaccines = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public List<Timeslot> getTimeslots() {
            return timeslots;
        }

        public void addTimeslot(Timeslot timeslot) {
            this.timeslots.add(timeslot);
        }

        public List<Vaccines> getVaccines() {
            return vaccines;
        }

        public void addVaccine(Vaccines vaccine) {
            this.vaccines.add(vaccine);
        }

        // You can also add methods to manage timeslots and vaccines within the center
        // such as removing them by ID or searching for specific timeslots/vaccines
    }



