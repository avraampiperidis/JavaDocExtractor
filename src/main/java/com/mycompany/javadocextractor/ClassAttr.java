/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javadocextractor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abraham
 */
public class ClassAttr {
    
    private List<ConstrAttr> constrlist;
    private List<MethodAttr> methods;
    private String classDescription;
    private String className;
    private int id;
    
    public ClassAttr() {
        constrlist = new ArrayList<>();
        methods = new ArrayList<>();
        classDescription = "";
    }
    
    
    public void setId(int i) {
        id = i;
    }
    
    public int getId() {
        return id;
    }
    
    public void setClassDesc(String s) {
        this.classDescription = s;
    }
    
    public void setClassName(String s) {
        this.className = s;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getClassDesc() {
        if(classDescription != null && !classDescription.isEmpty()) {
            return this.classDescription;
        }
        return "";
    }
    
    
    public void addMethod(MethodAttr m) {
        if(methods != null && !methods.isEmpty()) {
            methods.add(m);
        } else {
            methods = new ArrayList<>();
            methods.add(m);
        }
    }
    
    public void addConstr(ConstrAttr c) {
        if(constrlist != null && !constrlist.isEmpty()) {
            constrlist.add(c);
        } else {
            constrlist = new ArrayList<>();
            constrlist.add(c);
        }
    }

    /**
     * @return the constrlist
     */
    public List<ConstrAttr> getConstrlist() {
        return constrlist;
    }

    /**
     * @param constrlist the constrlist to set
     */
    public void setConstrlist(List<ConstrAttr> constrlist) {
        this.constrlist = constrlist;
    }

    /**
     * @return the methods
     */
    public List<MethodAttr> getMethods() {
        return methods;
    }

    /**
     * @param methods the methods to set
     */
    public void setMethods(List<MethodAttr> methods) {
        this.methods = methods;
    }
    
    
    
    
}
