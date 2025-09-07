package fit.se.thtuan03.model;

import java.time.LocalDate;
import java.util.List;

public class Student {
    public static class Qualification {
        private String exam;
        private String board;
        private String percentage;
        private String yearOfPassing;

        public Qualification(String exam, String board, String percentage, String yearOfPassing) {
            this.exam = exam;
            this.board = board;
            this.percentage = percentage;
            this.yearOfPassing = yearOfPassing;
        }

        public String getExam() { return exam; }
        public String getBoard() { return board; }
        public String getPercentage() { return percentage; }
        public String getYearOfPassing() { return yearOfPassing; }
    }

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String mobile;
    private String gender;
    private String address;
    private String city;
    private String pin;
    private String state;
    private String country;
    private List<String> hobbies;
    private List<Qualification> qualifications;
    private String course;

    public Student() {}

    public Student(String firstName, String lastName, LocalDate dob, String email,
                   String mobile, String gender, String address, String city,
                   String pin, String state, String country, List<String> hobbies,
                   List<Qualification> qualifications, String course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.pin = pin;
        this.state = state;
        this.country = country;
        this.hobbies = hobbies;
        this.qualifications = qualifications;
        this.course = course;
    }

    // Getters/Setters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDob() { return dob; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getGender() { return gender; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getPin() { return pin; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public List<String> getHobbies() { return hobbies; }
    public List<Qualification> getQualifications() { return qualifications; }
    public String getCourse() { return course; }
}
