package de.hems.tallerik.pi.sort.objects;

public class Person implements Comparable<Person> {
    private String name;
    private int tel;

    private boolean compareName;

    public Person(String name, int tel, boolean compareName) {
        this.name = name;
        this.tel = tel;
        this.compareName = compareName;
    }

    @Override
    public int compareTo(Person o) {
        if(compareName) {
            return this.getName().compareTo(o.getName());
        } else {
            return this.getTel() - o.getTel();
        }
    }

    @Override
    public String toString() {
        String printname = name;
        if(name.length() < 20) {
            int miss = 20 - name.length();
            for (int i = 0; i < miss; i++) {
                printname += " ";
            }
        }
        return "Person: name='" + printname + "' tel='" + tel+"\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}
