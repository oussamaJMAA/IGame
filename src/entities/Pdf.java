/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import service.CommandeCRUD;
import service.PanierCRUD;

/**
 *
 * @author Mariem
 */
public class Pdf {
    
    
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        CommandeCRUD cc=new CommandeCRUD();
        List<Commande> list=cc.displayCommmande();    
        document.add(new Paragraph("La liste des commandes :"));
        document.add(new Paragraph("     "));
         for(Commande u:list)
        {
            
        document.add(new Paragraph("ID_cmd :"+u.getId_cmd()));
        document.add(new Paragraph("ID_client :"+u.getIdClient()));
        document.add(new Paragraph("ID_Prd:"+u.getIdProduit()));
        document.add(new Paragraph("Prenom_user :"+u.getDate()));
        document.add(new Paragraph("Date :"+u.getLivraison()));
        document.add(new Paragraph("ModePaiment:"+u.getModePaiment()));
        document.add(new Paragraph("Etat :"+u.getEtat()));

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
     public void GeneratePdfp(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        PanierCRUD cc=new PanierCRUD();
        List<Panier> list=cc.displayPanier();    
        document.add(new Paragraph("La liste de panier :"));
        document.add(new Paragraph("     "));
         for(Panier u:list)
        {
            
        document.add(new Paragraph("ID_cmd :"+u.getIdP()));
        document.add(new Paragraph("ID_Prd:"+u.getIdProduit()));
        document.add(new Paragraph("Prenom_user :"+u.getNomProduit()));
        document.add(new Paragraph("Prix:"+u.getPrix()));
        document.add(new Paragraph("Image :"+u.getImage()));

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
}