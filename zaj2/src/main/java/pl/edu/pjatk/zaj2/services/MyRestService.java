package pl.edu.pjatk.zaj2.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MyRestService {
    private List<Zwierze> zwierzeta = new ArrayList<>();

    public MyRestService(){
        zwierzeta.add(new Pies("KArol", "szary"));
        zwierzeta.add(new Kot("Stefan", "rudy"));
    }

    public List<Zwierze> getZwierzeta() {
        return this.zwierzeta;
    }

    public void add(Zwierze zw) {
        this.zwierzeta.add(zw);
    }

    public void remove(String name) {
        for (Zwierze zw : zwierzeta) {
            if (zw.getName().equals(name)) {
                this.zwierzeta.remove(zw);
            }
        }
    }

    public List<Zwierze> getColor(String color) {
        List<Zwierze> zwierzetacolor = new ArrayList<>();
        for (Zwierze zw : zwierzeta) {
            if (zw.getColor().equals(color)) {
                zwierzetacolor.add(zw);
            }
        }
        return zwierzetacolor;
    }

    public void zmien(Zwierze zw) {
        for (Zwierze zwi : zwierzeta) {
            if (zwi.getName().equals(zw.getName())) {
                zwi.setColor(zw.getColor());

            }
        }
    }
}
