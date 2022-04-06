package be.intecbrussel;

import java.util.Comparator;

public class AgeComparator implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getAge() - o2.getAge();
    }
       /*  return -(o1.getAge() - o2.getAge());  1ste optie*/
    }


