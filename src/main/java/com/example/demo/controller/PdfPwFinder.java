package com.example.demo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;

public class PdfPwFinder {

    public static void main(String[] args)
    {

        final String[] dates = new String[]{ "01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31" };
        final String[] months = new String[]{ "01","02","03","04","05","06","07","08","09","10","11","12"};
        // select a file for Decryption operation
        File file = new File("C:\\gear\\workers\\zz\\mom.pdf");

        // Load the PDF file
        PDDocument pdd = null;
        String password = "";
        for(int i=0;i< dates.length;i++)
        {
            for(int j=0;j< months.length;j++)
            {
                //System.out.println("handler"+dates[i]+months[j]);
                password="handler"+dates[i]+months[j];

                try {
                    pdd = PDDocument.load(file, password);
                    // removing all security from PDF file
                    pdd.setAllSecurityToBeRemoved(true);

                    // Save the PDF file
                    pdd.save(file);

                    // Close the PDF file
                    pdd.close();
                    System.out.println("Decryption Done with ..."+ password);
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("Decryption FAILED with ..."+ password);
                }
            }
        }

    }

    public static void ebcrypt(String args[]) throws Exception {
        //Loading an existing document
        File file = new File("C:\\gear\\workers\\name.pdf");
        PDDocument document = PDDocument.load(file);

        //Creating access permission object
        AccessPermission ap = new AccessPermission();

        //Creating StandardProtectionPolicy object
        StandardProtectionPolicy spp = new StandardProtectionPolicy("1234", "1234", ap);

        //Setting the length of the encryption key
        spp.setEncryptionKeyLength(128);

        //Setting the access permissions
        spp.setPermissions(ap);

        //Protecting the document
        document.protect(spp);

        System.out.println("Document encrypted");

        //Saving the document
        document.save("C:\\gear\\workers\\zz\\name.pdf");
        //Closing the document
        document.close();

    }
}
