package com.example.rattaggingstudio;

import java.util.ArrayList;
import java.util.List;

public class Measurement {
    public List<String> fileNumber = new ArrayList<>();
    public List<String> description = new ArrayList<>();
//    public  Measurement(String fileNo, String descr){
//        this.fileNumber.add(fileNo);
//        this.description.add(descr);
//    }
    public void addFileNumber(String fileNo){
        this.fileNumber.add(fileNo);
    }
    public void addDescription(String descr){
        this.description.add(descr);
    }
    public List<String> getFileNumber(){
        return this.fileNumber;
    }
    public List<String>getDescription(){
        return this.description;
    }
}
