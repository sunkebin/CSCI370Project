import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private Patient[] patients;
    private int totalNumber;
    private int datasetID;

    public Dataset(Patient[] patients, int totalNumber, int datasetID) {
        this.patients = patients;
        this.totalNumber = totalNumber;
        this.datasetID = datasetID;
    }

    // Getter methods for All Attributes
    public Patient[] getPatients() {
        return patients;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public int getDatasetID() {
        return datasetID;
    }

    // Setter methods for All Attributes
    public void setPatients(Patient[] patients) {
        this.patients = patients;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public void setDatasetID(int datasetID) {
        this.datasetID = datasetID;
    }

    //Method to create new Dataset
    public Dataset NewDataSet(Patient[] patients, int totalNumber, int datasetID) {
        Dataset newDataset =  new Dataset(patients, totalNumber, datasetID);
        return newDataset;
    }

    //Method to read csv file
    public Dataset readFile(File file) throws IOException {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        //checking file extension
        if (!extension.equals("csv")) {
            throw new IOException("File format not supported.It must be CSV file");
        }

        //Reading data from file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Patient> patientList = new ArrayList<>();
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] patientData = line.split(",");
            int patientID = Integer.parseInt(patientData[0].trim());
            int age = Integer.parseInt(patientData[1].trim());
            int gender = Integer.parseInt(patientData[2].trim());
            double bmi = Double.parseDouble(patientData[3].trim());
            double bloodPressure = Double.parseDouble(patientData[4].trim());
            double totalSerumControl = Double.parseDouble(patientData[5].trim());
            double ldl = Double.parseDouble(patientData[6].trim());
            double hdl = Double.parseDouble(patientData[7].trim());
            double tch = Double.parseDouble(patientData[8].trim());
            double ltg = Double.parseDouble(patientData[9].trim());
            double glu = Double.parseDouble(patientData[10].trim());
            double diseaseProgressionValue = Double.parseDouble(patientData[11].trim());

            Patient patient = new Patient(age, gender, bmi, bloodPressure, totalSerumControl, ldl, hdl, tch, ltg, glu, diseaseProgressionValue);
            patientList.add(patient);
        }
        reader.close();

        //creating dataset
        Patient[] patients = new Patient[patientList.size()];
        patients = patientList.toArray(patients);
        int totalNumber = patients.length;
        int datasetID = (int) (Math.random());

        return new Dataset(patients, totalNumber, datasetID);
    }

    //Method to validate patient data
    public Patient validatePatientData(Patient patient) throws Exception {
        // Check age, BMI, and other integer/double fields
        if (patient.getAge() <= 0 || patient.getGender() <= 0 || patient.getBmi() <= 0 ||
                patient.getBloodPressure() <= 0 || patient.getTotalSerumCholesterol() <= 0 || patient.getLdl() <= 0 ||
                patient.getHdl() <= 0 || patient.getTch() <= 0 ||
                patient.getLtg() <= 0 || patient.getGlu() <= 0) {
            throw new Exception("Invalid patient data: age, BMI, or lab values are negative or 0");
        }
        // if All checks passed, patient data is valid
        return patient;
    }

}
