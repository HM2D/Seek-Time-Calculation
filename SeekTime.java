 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeekTime;

import static java.lang.Math.abs;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author HM2D
 */
public class SeekTime {
    
    
    
    
    
    public static void main(String[] args) {
      int n;
      System.out.println("How many requests?");
      Scanner in = new Scanner(System.in);
      n = in.nextInt();
      int[] sequence = new int[n];
      int discLimit = 200;
        System.out.println("Please enter the sequence:");
      for(int i=0;i<n;i++){
          System.out.print("Number " + i+ ":");
         sequence[i] = in.nextInt();
         if(sequence[i] > 200 || sequence[i] < 0){
             System.out.println("Request out of disc limit try again:");
             i--;
         
         }
      }
        for (int i = 0; i < n; i++) {
            System.out.println(sequence[i] + " ");
        }
        
     
        System.out.println("Start of the head:");
        int headStart = in.nextInt();
      int[] seekTimes = new int[n];
     // boolean start = false;
     
     
     ////FCFS
        System.out.println("FCFS:");
       // int tempCounter=0;
       int tempHeadStart = headStart;
       
        for (int i = 0; i < n; i++) {
           
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
        }
        float avg = 0;
        
        for (int i = 0; i < seekTimes.length; i++) {
            avg += seekTimes[i];
        }
        System.out.println("Avg Seek Time: " + avg/n);
        //RESET
        avg = 0;
        for (int i = 0; i < seekTimes.length; i++) {
            seekTimes[i] = 0;
        }
        //////////////////////
        System.out.println("SSTF:");
        int minSeekTime = 9999;
        int[] tempSeekTimes = new int[n];
        int[] tempSequence = new int[n];
        for (int i = 0; i < n; i++) {
            tempSequence[i] = sequence[i]; 
        }
        tempHeadStart = headStart;
        int index=0;
        for (int j = 0; j < n; j++) {
        
            for (int i = 0; i < n; i++) {
            
                tempSeekTimes[i] = abs(tempHeadStart - tempSequence[i]);
                if(minSeekTime > tempSeekTimes[i]){
                minSeekTime = tempSeekTimes[i];
                index = i;
            }
                
            }
         seekTimes[j] = minSeekTime;
         System.out.println("Head: " + tempHeadStart + " Request: " + sequence[index] + " SeekTime: " + seekTimes[j]);
          tempHeadStart = sequence[index];
            for (int i = 0; i < n; i++) {
                tempSeekTimes[i] = 0;
            }
                minSeekTime = 9999;
                tempSequence[index] = 9999;
               
                
        }
         //get average   
        for (int i = 0; i < seekTimes.length; i++) {
            avg += seekTimes[i];
        }
        System.out.println("Avg Seek Time: " + avg/n);
        //RESET
        avg = 0;
        for (int i = 0; i < seekTimes.length; i++) {
            seekTimes[i] = 0;
        }
        
        
        
        ///Look
        System.out.println("Look:");
        Arrays.sort(sequence);
        tempHeadStart = headStart;
        int headRestart=0;
        boolean equal = false;
        for (int i = 0; i < n; i++) {
            if(tempHeadStart == sequence[i]){
              index = i;
              equal = true; 
             break;
            }
            if(tempHeadStart < sequence[i]){
              index = i;
             
              break;
            }
        }
         boolean up= false;
         System.out.println(equal);
        if(equal){
        if((tempHeadStart - sequence[index-1]) > Math.abs((tempHeadStart -  sequence[index+1]))){
            up = true;
        }
        
        }else if((tempHeadStart - sequence[index-1]) > Math.abs((tempHeadStart -  sequence[index]))){
            up = true;
        }
        
        System.out.println(up);
         if(up){
         for (int i = index; i < n; i++) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        
       //headRestart = tempHeadStart;
       for (int i = index-1; i > 0; i--) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }}
        else{//must go down
         
         
        for (int i = index-1; i > 0; i--) {
            //System.out.println("itteration");
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        
        
        //tempHeadStart = 0;
        for (int i =index; i < n; i++) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
         
         
         
         }
         
        for (int i = 0; i < seekTimes.length; i++) {
            avg += seekTimes[i];
        }
        //avg += headRestart;
        System.out.println("Avg Seek Time: " + avg/n);
        
       //RESET
        avg = 0;
        for (int i = 0; i < seekTimes.length; i++) {
            seekTimes[i] = 0;
        }
        
        ///Scan
        System.out.println("SCAN:");
        Arrays.sort(sequence);
        tempHeadStart = headStart;
        //int headRestart=0;
        
        if(up){for (int i = index; i < n; i++) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        
       headRestart = discLimit - tempHeadStart;
       tempHeadStart = 200;
        for (int i = index-1; i > 0; i--) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }}else{//must go down
         
         
        for (int i = index-1; i > 0; i--) {
            //System.out.println("itteration");
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        
        headRestart = tempHeadStart;
        tempHeadStart = 0;
        for (int i =index; i < n; i++) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
         
         
         
         }
         
        for (int i = 0; i < seekTimes.length; i++) {
            avg += seekTimes[i];
        }
        avg += headRestart;
        System.out.println("Avg Seek Time: " + avg/n);
        
       //RESET
        avg = 0;
        for (int i = 0; i < seekTimes.length; i++) {
            seekTimes[i] = 0;
        }
        
        
        ///C-Scan  looks ok.
        
        System.out.println("C-SCAN:");
        Arrays.sort(sequence);
        tempHeadStart = headStart;
        
        if(up){
        for (int i = index; i < n; i++) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        
         headRestart = discLimit;
       tempHeadStart = 0;
        for (int i = 0; i < index; i++) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        for (int i = 0; i < seekTimes.length; i++) {
            avg += seekTimes[i];
        }
        avg += headRestart;
        System.out.println("Avg Seek Time: " + avg/n);
        
       //RESET
        avg = 0;
        for (int i = 0; i < seekTimes.length; i++) {
            seekTimes[i] = 0;
        }
        
        }else{//must go down
        
        for (int i = index-1; i > 0; i--) {
            //System.out.println("itteration");
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        
        
        tempHeadStart = 200;
        for (int i =sequence.length-1; i > index-1; i--) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        for (int i = 0; i < seekTimes.length; i++) {
            avg += seekTimes[i];
        }
        avg += discLimit;
        System.out.println("Avg Seek Time: " + avg/n);
        
       //RESET
        avg = 0;
        for (int i = 0; i < seekTimes.length; i++) {
            seekTimes[i] = 0;
        }
        
        
        
        
        
        
        
        
        }
        
        
        
        
        System.out.println("C-LOOK:");
        Arrays.sort(sequence);
        tempHeadStart = headStart;
        
        if(up){//must go up
        for (int i = index; i < n; i++) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        
         headRestart = sequence[n-1] - sequence[0];
       tempHeadStart = sequence[0];
        for (int i = 0; i < index; i++) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        for (int i = 0; i < seekTimes.length; i++) {
            avg += seekTimes[i];
        }
        avg += headRestart;
        System.out.println("Avg Seek Time: " + avg/n);
        
       //RESET
        avg = 0;
        for (int i = 0; i < seekTimes.length; i++) {
            seekTimes[i] = 0;
        }
        
        }else{//must go down
        
        for (int i = index-1; i > 0; i--) {
            //System.out.println("itteration");
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        
        headRestart = sequence[n-1] - tempHeadStart;
        tempHeadStart = sequence[n-1];
        for (int i =sequence.length-1; i > index-1; i--) {
            
            seekTimes[i] = abs(tempHeadStart - sequence[i]);
                
                System.out.println("Head: " + tempHeadStart + " Request: " + sequence[i] + " SeekTime: " + seekTimes[i]);
                tempHeadStart = sequence[i];
       
        }
        for (int i = 0; i < seekTimes.length; i++) {
            avg += seekTimes[i];
        }
        avg += headRestart;
        System.out.println("Avg Seek Time: " + avg/n);
        
       //RESET
        avg = 0;
        for (int i = 0; i < seekTimes.length; i++) {
            seekTimes[i] = 0;
        }
        
        
        
        
        
        
        
        
        }
        
        
        
        
        
        
        
        
        
        
            
            
            
         
        
        
        
        }
      
      
      //
      
    }
    
