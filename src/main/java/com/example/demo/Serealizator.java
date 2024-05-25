
package com.example.demo;

import java.io.*;

    public class Serealizator {

        public boolean serealization(){

            boolean flag = false;

            File file = new File("C:\\Users\\38098\\IdeaProjects\\demo\\ser");

            ObjectOutputStream oos = null;

            try {
                FileOutputStream fos = new FileOutputStream(file);
                for(Soldier el : Main.arrSoldiers){
                    if(fos != null) {
                        oos = new ObjectOutputStream(fos);
                        oos.writeObject(el);
                        flag = true;
                        System.out.println(Main.arrSoldiers.toString());
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                if(oos != null){
                    try {
                        oos.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            return flag;
        }

        public Soldier deserialization() throws InvalidObjectException {
            File file = new File("C:\\Users\\38098\\IdeaProjects\\demo\\ser");
            ObjectInputStream ois = null;
            try {
                FileInputStream fis = new FileInputStream(file);
                for(Soldier el : Main.arrSoldiers) {
                    if (fis != null) {
                        ois = new ObjectInputStream(fis);
                        Soldier stranger = (Soldier) ois.readObject();
                        System.out.println(stranger);
                        return stranger;
                    }
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e ){
                e.printStackTrace();
            }
            finally{
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            throw new InvalidObjectException("Object fail");
        }

}
