
public class Patient {
    private int patientID;
    private int age;
    private String gender;
    private double bmi;
    private String bloodPressure;
    private double totalSerumControl;
    private double ldl;
    private double hdl;
    private double tch;
    private double ltg;
    private double glu;
    private double diseaseProgressionValue;

    public Patient(int age, String gender, double bmi, String bloodPressure, double totalSerumControl, double ldl, double hdl, double tch, double ltg, double glu, double diseaseProgressionValue) {
        this.age = age;
        this.gender = gender;
        this.bmi = bmi;
        this.bloodPressure = bloodPressure;
        this.totalSerumControl = totalSerumControl;
        this.ldl = ldl;
        this.hdl = hdl;
        this.tch = tch;
        this.ltg = ltg;
        this.glu = glu;
        this.diseaseProgressionValue = diseaseProgressionValue;
    }

    //gettter Method for All Attributes
    public int getPatientID() {
        return patientID;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getBmi() {
        return bmi;
    }

    public double getTotalSerumControl() {
        return totalSerumControl;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public double getLdl() {
        return ldl;
    }

    public double getHdl() {
        return hdl;
    }

    public double getTch() {
        return tch;
    }

    public double getLtg() {
        return ltg;
    }

    public double getGlu() {
        return glu;
    }

    public double getDiseaseProgressionValue() {
        return diseaseProgressionValue;
    }

    //Setter Methods for All Attributes
    public void setLdl(double ldl) {
        this.ldl = ldl;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setTotalSerumControl(double totalSerumControl) {
        this.totalSerumControl = totalSerumControl;
    }

    public void setHdl(double hdl) {
        this.hdl = hdl;
    }

    public void setTch(double tch) {
        this.tch = tch;
    }

    public void setLtg(double ltg) {
        this.ltg = ltg;
    }

    public void setGlu(double glu) {
        this.glu = glu;
    }

    public void setDiseaseProgressionValue(double diseaseProgressionValue) {
        this.diseaseProgressionValue = diseaseProgressionValue;
    }

    //Method to create new patient with given details
    public Patient NewPatient(int age, String gender, double bmi, String bloodPressure, double totalSerumControl, double ldl, double hdl, double tch, double ltg, double glu, double diseaseProgressionValue) {
        Patient newPatient = new Patient(age, gender, bmi, bloodPressure, totalSerumControl, ldl, hdl, tch, ltg, glu, diseaseProgressionValue);
        return newPatient;
    }

}
