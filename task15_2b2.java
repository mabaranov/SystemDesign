package com.company;

import java.util.ArrayList;
import java.util.List;

public class Human
{
    private String Name;
    public int Age;

    public String About() {
        return "My name "+this.Name+". Age: "+this.Age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

public class Man extends Human {
    public String Sex = "man";

    @Override
    public String About() {
        return super.About() + "I`m " + this.Sex;
    }
}

public class Woman extends Human
{
    public String Sex = "woman";

    @Override
    public String About() { return super.About() + "I`m " + this.Sex; }
}

public class Main {
    public static void main(String[] args) {

        var humansList = new ArrayList<Human>();
        humansList.add(new Man() );
        humansList.add(new Woman() );

        for (int i=0; i<humansList.size(); i++) {
            System.out.println(humansList.get(i).About());
        }
    }
}
