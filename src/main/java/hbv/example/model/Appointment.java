package hbv.example.model;

public class Appointment  {

    private int id;
    private int userId;
    private Integer relativeId;
    private int timeslotId;
    private int vaccineId;
    private int vaccinationCenterId;
    private String bookingDate;

    private User user;
   private Relative relative;
    private Timeslot timeslot;
    private Vaccines vaccine;
    private VaccinationCenter vaccinationCenter;

    public Appointment() {
    }

    public Appointment( int userId, Integer relativeId, int timeslotId, int vaccineId, int vaccinationCenterId, String bookingDate) {
//        this.id = id;
        this.userId = userId;
        this.relativeId = relativeId;
        this.timeslotId = timeslotId;
        this.vaccineId = vaccineId;
        this.vaccinationCenterId = vaccinationCenterId;
        this.bookingDate = bookingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Integer relativeId) {
        this.relativeId = relativeId;
    }

    public int getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public int getVaccinationCenterId() {
        return vaccinationCenterId;
    }

    public void setVaccinationCenterId(int vaccinationCenterId) {
        this.vaccinationCenterId = vaccinationCenterId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
  }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Vaccines getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccines vaccine) {
        this.vaccine = vaccine;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    // Additional methods specific to Appointment functionality (optional)
}
//{
//}
