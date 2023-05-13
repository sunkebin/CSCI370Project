
public class Patient {
    int patientID;
    int age;
    int gender; //0 male; 1 female
    double bmi;
    double bloodPressure;
    double totalSerumCholesterol;
    double LDL, HDL, TCH, LTG,GLU;
    double diseaseProgressionValue;
    Patient next;

    public Patient(int ID, int a, int g, double b, double bp, double tsc, double lDL, double hDL, double tCH, double lTG, double gLU, double diseaseProgressionValue) {
        patientID = ID;
        age = a;
        gender = g;
        bmi = b;
        bloodPressure = bp;
        totalSerumCholesterol = tsc;
        LDL = lDL;
        HDL = hDL;
        TCH = tCH;
        LTG = lTG;
        GLU = gLU;
        this.diseaseProgressionValue = diseaseProgressionValue;
        this.next = null;
    }

    public Patient() {//dummy
        patientID = -1;
        age = 0;
        gender = -1;
        bmi = 0;
        bloodPressure = 0;
        totalSerumCholesterol = 0;
        LDL = 0;
        HDL = 0;
        TCH = 0;
        LTG =0;
        GLU = 0;
        diseaseProgressionValue =0;
        next = null;
    }
}
