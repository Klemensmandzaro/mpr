package pl.edu.pjatk.zaj2.service;



public class Zwierze {

    Long id;
    String name;
    String color;
    int identyfikator;

    public Zwierze(String name, String color) {
        this.name = name;
        this.color = color;
        this.setIdentyfikator();
    }

    public Zwierze() {

    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getIdentyfikator() {
        return identyfikator;
    }

    public void setIdentyfikator() {
        int id = 0;
        char[] chars = name.toCharArray();
        char[] chars2 = color.toCharArray();
        for (char c : chars) {
            id+= c;
        }
        for (char c : chars2) {
            id+= c;
        }
        this.identyfikator = id;
    }
}
