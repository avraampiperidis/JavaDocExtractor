/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javadocextractor;

/**
 *
 * @author abraham
 */
public class MethodAttr {
    
    private String methodDesc;
    private String returnType;
    private String methodBody;
    private int id;
    private String methodclass;
   
    
    public MethodAttr() {
        
    }
    
    public void setMethodClass(String s) {
        this.methodclass = s;
    }
    
    public String getMethodClass() {
        return methodclass;
    }
    
    public void setId(int i) {
        id = i;
    }
    
    public int getId() {
        return id;
    }
    
    public void setReturnType(String s) {
        returnType = s;
    }
    
    public String getReturnType() {
        return returnType;
    }
    
    public void setMethodBody(String b) {
        methodBody = b;
    }
    
    public String getMethodBody() {
        return methodBody;
    }
    
    public String getReturnTypeAndMethodBody() {
        return returnType + " " + methodBody;
    }
    
    
    public void setMethodDesc(String meths) {
        this.methodDesc = meths;
    }
    
    public String getMethodDesc() {
        if(methodDesc != null && !methodDesc.isEmpty()) {
            return methodDesc;
        }
        return "";
    }
    
}
