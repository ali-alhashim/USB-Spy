/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usbcopier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.filechooser.FileSystemView;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ALI
 */
public class USBCopier {
    
    static File[] oldListRoot = File.listRoots();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        USBCopier.waitForNotifying();
        
    }
    
    
   
    
    
    public static void waitForNotifying() {
    Thread t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (File.listRoots().length > oldListRoot.length) {
                    
                    System.out.println("new drive detected");
                    
                    oldListRoot = File.listRoots();
                    
                    System.out.println(oldListRoot[oldListRoot.length-2]);
                    
                    
                    File[] drives = File.listRoots();
                   if (drives != null && drives.length > 0) 
                   {
                      for (File aDrive : drives) 
                      {
                        System.out.println(aDrive);
                        FileSystemView fsv = FileSystemView.getFileSystemView();
                        String driveType = fsv.getSystemTypeDescription(aDrive);
                        
                        
                        
                        if("USB Drive".equals(driveType))
                        {
                            System.out.println(driveType);
                            
                            File usbDrive =  aDrive.getAbsoluteFile();
                            
                            File [] usbFiles = usbDrive.listFiles();
                        
                        
                        for(int i =0; i < usbFiles.length; i++)
                        {
                            System.out.println(usbFiles[i]);
                            
                            if(usbFiles[i].isDirectory())
                            {
                                  File destDir =  new File("C:\\Windows10\\USBCopier\\"+usbFiles[i].getName());
                                  try
                                  {
                                  FileUtils.copyDirectory(usbFiles[i],destDir);
                                  }
                                  catch(IOException e)
                                  {
                                      System.out.println(e);
                                  }
                            }
                            
                            try
                            {
                            Path CopyTo =  Paths.get("C:\\Windows10\\USBCopier\\"+usbFiles[i].getName());
                            
                            Files.copy(usbFiles[i].toPath(),CopyTo);
                            }
                            catch(IOException e)
                            {
                                System.out.println(e);
                            }
                        }
                        
                        } // end usb
                        
                        
                       }
                   }
                    
                    
                    
                    
                   
                        //----------------------------------------------------------------------------------
                        
                       //("oldListRoot[oldListRoot.length-2].toString()");
                        
                        //  C:\Users\ALI\Downloads\USBTest
                        
                       
                        
                        
                        
                        
                           
                        
                          
                        //-------------------------------------------------------------------------------------
                    
                    

                }
                else if (File.listRoots().length < oldListRoot.length) {
    System.out.println(oldListRoot[oldListRoot.length-2]+" drive removed");

                    oldListRoot = File.listRoots();

                }

            }
        }
    });
    t.start();
}
    
}
