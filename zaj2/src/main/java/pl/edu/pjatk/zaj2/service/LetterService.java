package pl.edu.pjatk.zaj2.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class LetterService {


    
    public String upper(String str) {
        return str.toUpperCase();
    }

    public String lower(String str) {
        str = str.toLowerCase();

        str = str.substring(0, 1).toUpperCase()+str.substring(1).toLowerCase();
        return str;
    }
}
