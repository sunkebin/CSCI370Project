import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dataset {
    public List<Patient> patients;
    public int totalNumber;
    public int datasetID;
    public Map<String, Double> medians;


    public Dataset(List<Patient> patients, int totalNumber, int datasetID) {
        this.patients = patients;
        this.totalNumber = totalNumber;
        this.datasetID = datasetID;
    }

    // Getter methods for All Attributes
    public List<Patient> getPatients() {
        return patients;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public int getDatasetID() {
        return datasetID;
    }

    // Setter methods for All Attributes
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public void setDatasetID(int datasetID) {
        this.datasetID = datasetID;
    }

    //Method to create new Dataset
    public Dataset NewDataSet(List<Patient> patients, int totalNumber, int datasetID) {
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
        int patientIDCounter = 1;
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] patientData = line.split(",");
            int patientID = patientIDCounter++;
            int age = Integer.parseInt(patientData[0].trim());
            int gender = Integer.parseInt(patientData[1].trim());
            double bmi = Double.parseDouble(patientData[2].trim());
            double bloodPressure = Double.parseDouble(patientData[3].trim());
            double totalSerumControl = Double.parseDouble(patientData[4].trim());
            double ldl = Double.parseDouble(patientData[5].trim());
            double hdl = Double.parseDouble(patientData[6].trim());
            double tch = Double.parseDouble(patientData[7].trim());
            double ltg = Double.parseDouble(patientData[8].trim());
            double glu = Double.parseDouble(patientData[9].trim());
            double diseaseProgressionValue = Double.parseDouble(patientData[10].trim());

            Patient patient = new Patient(age, gender, bmi, bloodPressure, totalSerumControl, ldl, hdl, tch, ltg, glu, diseaseProgressionValue);
            patientList.add(patient);
        }
        reader.close();

        calculateAndStoreMedians(patientList);

        //creating dataset
        patients = patientList;
        int totalNumber = patients.size();
        int datasetID = (int) (Math.random());

        return new Dataset(patients, totalNumber, datasetID);
    }

    //Method to validate patient data
    public Patient validatePatientData(Patient patient) throws Exception {
        // Check age, BMI, and other integer/double fields
        if (patient.getAge() <= 0) {
            double medianAge = medians.get("age"); // Get the median age
            patient.setAge((int) medianAge); // Set age to the median value
        }

        if (patient.getGender() <= 0) {
            double medianGender = medians.get("gender"); // Get the median gender
            patient.setGender((int) medianGender); // Set gender to the median value
        }

        if (patient.getBmi() <= 0) {
            double medianBmi = medians.get("bmi"); // Get the median BMI
            patient.setBmi(medianBmi); // Set BMI to the median value
        }

        if (patient.getBloodPressure() <= 0) {
            double medianBloodPressure = medians.get("bloodPressure"); // Get the median blood pressure
            patient.setBloodPressure(medianBloodPressure); // Set blood pressure to the median value
        }

        if (patient.getTotalSerumCholesterol() <= 0) {
            double medianCholesterol = medians.get("cholesterol"); // Get the median cholesterol
            patient.setTotalSerumCholesterol(medianCholesterol); // Set cholesterol to the median value
        }

        if (patient.getLdl() <= 0) {
            double medianLdl = medians.get("ldl"); // Get the median LDL
            patient.setLdl(medianLdl); // Set LDL to the median value
        }

        if (patient.getHdl() <= 0) {
            double medianHdl = medians.get("hdl"); // Get the median HDL
            patient.setHdl(medianHdl); // Set HDL to the median value
        }

        if (patient.getTch() <= 0) {
            double medianTch = medians.get("tch"); // Get the median TCH
            patient.setHdl(medianTch); // Set TCH to the median value
        }

        if (patient.getLtg() <= 0) {
            double medianLtg = medians.get("ltg"); // Get the median LTG
            patient.setHdl(medianLtg); // Set LTG to the median value
        }

        if (patient.getGlu() <= 0) {
            double medianGlu = medians.get("glu"); // Get the median GLU
            patient.setHdl(medianGlu); // Set GLU to the median value
        }

        // if All checks passed, patient data is valid
        return patient;
    }

    public void calculateAndStoreMedians(List<Patient> patientList) {
        medians = calculateMedians(patientList);
    }

    public Map<String, Double> calculateMedians(List<Patient> patientList) {
        Map<String, List<Double>> criterionValues = new HashMap<>();

        // Initialize the criterion values map
        criterionValues.put("age", new ArrayList<>());
        criterionValues.put("gender", new ArrayList<>());
        criterionValues.put("bmi", new ArrayList<>());
        criterionValues.put("bloodPressure", new ArrayList<>());
        criterionValues.put("cholesterol", new ArrayList<>());
        criterionValues.put("ldl", new ArrayList<>());
        criterionValues.put("hdl", new ArrayList<>());
        criterionValues.put("tch", new ArrayList<>());
        criterionValues.put("ltg", new ArrayList<>());
        criterionValues.put("glu", new ArrayList<>());


        // Collect the values for each criterion
        for (Patient patient : patientList) {
            criterionValues.get("age").add((double) patient.getAge());
            criterionValues.get("gender").add((double) patient.getGender());
            criterionValues.get("bmi").add(patient.getBmi());
            criterionValues.get("bloodPressure").add(patient.getBloodPressure());
            criterionValues.get("cholesterol").add(patient.getTotalSerumCholesterol());
            criterionValues.get("ldl").add(patient.getLdl());
            criterionValues.get("hdl").add(patient.getHdl());
            criterionValues.get("tch").add(patient.getHdl());
            criterionValues.get("ltg").add(patient.getHdl());
            criterionValues.get("glu").add(patient.getHdl());
        }

        // Calculate the medians for each criterion
        Map<String, Double> medians = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : criterionValues.entrySet()) {
            String criterion = entry.getKey();
            List<Double> values = entry.getValue();
            double median = calculateMedian(values);
            medians.put(criterion, median);
        }

        return medians;
    }

    public double calculateMedian(List<Double> values) {
        Collections.sort(values);
        int size = values.size();
        int middle = size / 2;
        if (size % 2 == 0) {
            double median1 = values.get(middle - 1);
            double median2 = values.get(middle);
            return (median1 + median2) / 2.0;
        } else {
            return values.get(middle);
        }
    }


}
