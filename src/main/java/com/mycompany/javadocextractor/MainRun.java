/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javadocextractor;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author abraham
 */
public class MainRun {
    
    public static void main(String args[]) throws IOException {
        
        List<ClassAttr> classes = new ArrayList<>();   
        System.out.println("Scanning... please wait ,this may take long..");
        for(int f =0; f < Paths.paths.length; f++) {
            File file = new File(Paths.paths[f]);
            File[] files = file.listFiles();
            for(int i =0; i< files.length; i++) {
                if(files[i].isFile() && Character.isUpperCase(files[i].getName().charAt(0))) {
                    ClassAttr cls = new ClassAttr();
                    Document doc = Jsoup.parse(files[i],"utf-8");
                    Elements element = doc.getElementsByClass("block");
                    String classname = files[i].getName().substring(0, files[i].getName().lastIndexOf("."));
                    //System.out.println("||-------->"+classname);
                    cls.setClassName(classname);
                    List<MethodAttr> meths = new ArrayList<>();
                    Elements els = doc.select("tr[id~=(i)[0-9]");
                    for(int x =0; x < els.size(); x++) {
                        if(!els.isEmpty()) {
                            //System.out.println(els.get(x).text());
                            MethodAttr meth = new MethodAttr();
                            Elements mname = els.get(x).getElementsByTag("code");
                            Elements methdesc = els.get(x).getElementsByTag("div");
                            meth.setMethodDesc(methdesc.text());
                            meth.setReturnType(mname.get(0).text());
                            meth.setMethodBody(mname.get(1).text());
                            meth.setMethodClass(classname);
                            meths.add(meth);
                        }
                    }
                    if(element != null && !element.isEmpty()) {
                        //System.out.println(element.get(0).text());
                        //System.out.println();
                        cls.setClassDesc(element.get(0).text());
                    }
                    cls.setMethods(meths);
                    classes.add(cls);
                }
            }
        }
        System.out.println("Number of total classes scanned = "+classes.size());
        int totalmethods =0;
        for(int i =0; i < classes.size(); i++) {
            totalmethods += classes.get(i).getMethods().size();
        }
        System.out.println("num of total methods scanned= "+totalmethods);
        Connection c = null;
        Statement stmt = null;
        try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:javadoc.db");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");
                System.out.println("Writing to sqlite database...");
                stmt = c.createStatement();
                initTables(stmt);
                String sql = "";   
                for(int i =0; i < classes.size(); i++) {
                    sql = "insert into classentry(classname,description) " +
                            "values('"+classes.get(i).getClassName()+"','"+classes.get(i).getClassDesc().replaceAll("'","''")+"');";
                    //System.out.println(classes.get(i).getClassName());
                    stmt.executeUpdate(sql);
                    List<MethodAttr> meths = classes.get(i).getMethods();
                    for(int y =0; y < meths.size(); y++) {
                        sql = "insert into methodentry(methodtype,methodname,methoddesc,methodclass) values ('"+meths.get(y).getReturnType().replaceAll("'","''") + "',"
                                + " '"+meths.get(y).getMethodBody().replaceAll("'","''")+"', "
                                + " '"+meths.get(y).getMethodDesc().replaceAll("'","''")+"', "
                                + " '"+meths.get(y).getMethodClass().replaceAll("'","''")+"');";
                        stmt.executeUpdate(sql);
                    }
                }
                c.commit();
                stmt.close();
                c.close();
                System.out.println("Finish with status=Success");
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void initTables(Statement stmt) throws SQLException {
        String sql = "DROP TABLE IF EXISTS methodentry";
        stmt.execute(sql);
        sql = "DROP TABLE IF EXISTS classentry";
        stmt.execute(sql);
        stmt.execute(TABLE_CLASS);
        stmt.execute(TABLE_METHOD);
    }
    
    private static String TABLE_CLASS = "CREATE TABLE classentry(classname TEXT NOT NULL,description TEXT,id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT);";
    private static String TABLE_METHOD = "CREATE TABLE methodentry(methodtype TEXT,methodname TEXT,methoddesc TEXT,methodclass TEXT,id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT)";
    
    
}
