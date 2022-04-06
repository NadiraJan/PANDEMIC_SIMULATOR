package be.intecbrussel;

import java.util.*;
import java.util.stream.Collectors;

public class PatientApp {
    public static void main(String[] args) {

        List<Patient> asPatientList = Patient.getAllPatients();
      /*  asPatientList.forEach(System.out::println);
        System.out.println("----------------------------------------------");*/

        Set<Patient> sortedPatientList = new HashSet<>(Patient.getAllPatients());
        System.out.println("DUPLICATES SORTED LIST: ");
        sortedPatientList.forEach(System.out::println);

        System.out.println("--------------------------------------------");


        Set<Patient> patientList = new TreeSet<>(
                Comparator.comparing(Patient::getTemperature).reversed().
                        thenComparing(Patient::getAge).
                        thenComparing(Patient::isEnsured)
        );

        patientList.addAll(Patient.getAllPatients());
        System.out.println("SORTED LIST BY HIGH TEMPERATURE: ");

        patientList.forEach(System.out::println);
        System.out.println("------------------------------------------------------");

        PriorityQueue<Patient> isEnsured = new PriorityQueue<Patient>(new EnsuredComparator());


        List<Patient> Category1 = patientList.stream().filter(Patient
                        -> Patient.isUnKnownVirus())
                .filter(patient -> (patient.getAge() >= 65 && patient.getTemperature() >= 38) ||
                        (patient.getAge() <= 65 && patient.getTemperature() >= 38 || patient.getAge() >= 65 &&
                                patient.getTemperature() < 38 || patient.getTemperature() >= 40 && patient.isUnKnownVirus()))

                .sorted(Comparator.comparingInt(Patient::getAge)).
                sorted(Comparator.comparing(Patient::getTemperature))
                .collect(Collectors.toList());

        for (Patient p : Category1) {

            System.out.println("CATEGORY 1 " + p);

        }
        System.out.println("---------------------------------------------------------");

        List<Patient> Category2 = patientList.stream().filter(Patient
                        -> Patient.isUnKnownVirus())
                .filter(patient -> (patient.getTemperature() <= 38 && patient.getAge() < 65 ||
                        patient.isUnKnownVirus() && patient.getAge() < 65 && patient.getTemperature() >= 38)
                )

                .sorted(Comparator.comparing(Patient::getTemperature))
                .sorted(Comparator.comparing(Patient::isUnKnownVirus))
                .sorted(Comparator.comparing(Patient::getAge))
                .collect(Collectors.toList());

        for (Patient p : Category2) {

            System.out.println("CATEGORY 2 " + p);

        }
        System.out.println("------------------------------------------------------");

        List<Patient> Category3 = patientList.stream().filter(Patient
                        -> Patient.isUnKnownVirus())
                .filter(patient -> (patient.getTemperature() < 38 && patient.isUnKnownVirus()))

                .sorted(Comparator.comparing(Patient::getTemperature)).
                sorted(Comparator.comparing(Patient::isUnKnownVirus))
                .collect(Collectors.toList());

        for (Patient p : Category3) {

            System.out.println("CATEGORY 3 " + p);

        }
        System.out.println("------------------------------------------------------");

        List<Patient> Category4 = patientList.stream().filter(Patient
                        -> !Patient.isUnKnownVirus())
                .filter(patient -> (patient.getTemperature() >= 38 && !patient.isUnKnownVirus()))

                .sorted(Comparator.comparing(Patient::getTemperature)).
                sorted(Comparator.comparing(Patient::isUnKnownVirus))
                .collect(Collectors.toList());

        for (Patient p : Category4) {

            System.out.println("CATEGORY 4 " + p);

        }
        System.out.println("------------------------------------------------------");

        List<Patient> NotInCategory = patientList.stream().filter(Patient
                        -> !Patient.isUnKnownVirus())
                .filter(patient -> (patient.getTemperature() < 38 && !patient.isUnKnownVirus()))

                .sorted(Comparator.comparing(Patient::getTemperature)).
                sorted(Comparator.comparing(Patient::isUnKnownVirus))
                .collect(Collectors.toList());

        for (Patient p : NotInCategory) {

            System.out.println("Go to Pharmacy or home:  " + p);
        }
        System.out.println("-----------------------------------------------------");


    }
}

























