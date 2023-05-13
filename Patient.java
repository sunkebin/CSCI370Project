
public class Patient {
    private int patientID;
    private int age;
    private int gender;
    private double bmi;
    private double bloodPressure;
    private double totalSerumCholesterol;
    private double ldl;
    private double hdl;
    private double tch;
    private double ltg;
    private double glu;
    private double diseaseProgressionValue;

    public Patient(int age, int gender, double bmi, double bloodPressure, double totalSerumCholesterol, double ldl, double hdl, double tch, double ltg, double glu, double diseaseProgressionValue) {
        this.age = age;
        this.gender = gender;
        this.bmi = bmi;
        this.bloodPressure = bloodPressure;
        this.totalSerumCholesterol = totalSerumCholesterol;
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

    public int getGender() {
        return gender;
    }

    public double getBmi() {
        return bmi;
    }

    public double getTotalSerumCholesterol() {
        return totalSerumCholesterol;
    }

    public double getBloodPressure() {
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

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setTotalSerumCholesterol(double totalSerumCholesterol) {
        this.totalSerumCholesterol = totalSerumCholesterol;
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

    public Patient NewPatient(int age, int gender, double bmi, double bloodPressure, double totalSerumCholesterol, double ldl, double hdl, double tch, double ltg, double glu, double diseaseProgressionValue) {
        Patient newPatient = new Patient(age, gender, bmi, bloodPressure, totalSerumCholesterol, ldl, hdl, tch, ltg, glu, diseaseProgressionValue);
        return newPatient;
    }

    public double getCriterionValue(String criterion) {
        if (criterion.equals("age")) {
            return age;
        } else if (criterion.equals("gender")) {
            return gender;
        } else if (criterion.equals("bmi")) {
            return bmi;
        } else if (criterion.equals("bloodPressure")) {
            return bloodPressure;
        } else if (criterion.equals("totalSerumCholesterol")) {
            return totalSerumCholesterol;
        } else if (criterion.equals("ldl")) {
            return ldl;
        } else if (criterion.equals("hdl")) {
            return hdl;
        } else if (criterion.equals("tch")) {
            return tch;
        } else if (criterion.equals("ltg")) {
            return ltg;
        } else if (criterion.equals("glu")) {
            return glu;
        } else {
            return 0.0; // dummy
        }
    }
}
