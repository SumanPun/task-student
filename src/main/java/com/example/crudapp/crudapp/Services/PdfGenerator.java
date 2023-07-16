package com.example.crudapp.crudapp.Services;

import java.io.IOException;
import java.util.List;

import com.example.crudapp.crudapp.Entity.Student;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import jakarta.servlet.http.HttpServletResponse;

public class PdfGenerator {
	
	
	
	public void generate(List<Student> studentsList, HttpServletResponse response) throws DocumentException, IOException  {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);
		
		Font fontTitle1 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(16);

		Font color_black = FontFactory.getFont(FontFactory.COURIER_BOLD,14f,BaseColor.BLACK);
		Font color_red = FontFactory.getFont(FontFactory.COURIER_BOLD,14f,BaseColor.RED);
		Font color_normal_red = FontFactory.getFont(FontFactory.TIMES_ROMAN,12f,BaseColor.RED);
		Font color_normal = FontFactory.getFont(FontFactory.TIMES_ROMAN,12f);

		Chunk glue = new Chunk(new VerticalPositionMark());
		Paragraph paragraph1 = new Paragraph("Quote No:#1005", fontTitle);
		paragraph1.add(new Chunk(glue));
		paragraph1.add("AusNep IT Solutions \n Maitidevi, Kathmandu \n Phone: 9876543210 \n info@ausnepit.com.au");
		paragraph1.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph1);
		
		Paragraph lineSpace = new Paragraph("\n"+ "\n");
		//lineSpace.setAlignment(Paragraph.ALIGN_MIDDLE);
		document.add(lineSpace);

		Paragraph paragraph2 = new Paragraph("Attr: ", color_black);
		paragraph2.add(new Chunk("Florence Gibbs", color_normal));
		paragraph2.add(new Chunk(glue));
		paragraph2.add(new Chunk("Date: 2022-12-21", color_normal));
		document.add(paragraph2);



		Paragraph paragraph3 = new Paragraph("Phone: ", color_black);
		paragraph3.add(new Chunk("1234567890", color_normal));
		paragraph3.add(new Chunk(glue));
		paragraph3.add(new Chunk("Quote Valid Until: ", color_red));
		paragraph3.add(new Chunk("2022-12-21", color_normal_red));
		document.add(paragraph3);
		document.add(new Chunk("Email: ", color_black));
		document.add(new Chunk("maloqaze1ze@mallinator.net", color_normal));
		
		Paragraph dotedLine = new Paragraph("\n"+ "\n" +"-----------------------------------------------------------------------------------------------------------------------------------"+"\n" + "\n", fontTitle1);
		dotedLine.setAlignment(Paragraph.ALIGN_MIDDLE);
		document.add(dotedLine);

		Paragraph paragraph4 = new Paragraph("Nature Of Project: ", color_black);
		paragraph4.add(new Chunk("Test", color_normal));
		document.add(paragraph4);

		Paragraph space = new Paragraph("\n" + "\n");
		lineSpace.setAlignment(Paragraph.ALIGN_MIDDLE);
		document.add(space);

		Paragraph paragraph5 = new Paragraph("Project Feature: ", color_black);
		paragraph5.add(new Chunk("project Feature", color_normal));
		document.add(paragraph5);

		document.add(space);

		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] {3,3,3,3,3,3,3,3});
		table.setSpacingBefore(5);
		table.setSpacingAfter(30);
		table.getDefaultCell().setBorder(0);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.BLACK);
		cell.setPadding(5);
	    // Creating font
	    // Setting font style and size
	    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.WHITE);
	    // Adding headings in the created table cell or  header
	    // Adding Cell to table
	    cell.setPhrase(new Phrase("ID", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Student Name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Email", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Semester", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Address", font));
	    table.addCell(cell);
		cell.setPhrase(new Phrase("imgName", font));
		table.addCell(cell);
	    cell.setPhrase(new Phrase("Active", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Created_At", font));
	    table.addCell(cell);
	    // Iterating the list of students
	    for (Student student: studentsList) {
	      // Adding student id
	      table.addCell(String.valueOf(student.getId()));
	      // Adding student name
	      table.addCell(student.getName());
	      // Adding student email
	      table.addCell(student.getEmail());
	      // Adding student mobile
	      table.addCell(student.getSemester());
	      table.addCell(student.getAddress());
		  table.addCell(student.getImageName());
	      table.addCell(student.getActive().toString());
	      table.addCell(student.getAddedDate().toString());
	     
	    }

	    // Adding the created table to the document
	    document.add(table);
		DottedLineSeparator dottedLineSeparator1 = new DottedLineSeparator();
		dottedLineSeparator1.setGap(3);
		document.add(dottedLineSeparator1);

		Paragraph paragraph12 = new Paragraph("Total", color_black);
		paragraph12.add(new Chunk("            "+studentsList.size(), color_black));
		paragraph12.setAlignment(Element.ALIGN_RIGHT);
		paragraph12.setSpacingAfter(10);
		document.add(paragraph12);
		document.add(dottedLineSeparator1);

		document.add(new Paragraph("\n "));

		DottedLineSeparator dottedLineSeparator = new DottedLineSeparator();
		dottedLineSeparator.setGap(0);
		document.add(dottedLineSeparator);
		document.add(space);

		Paragraph paragraph6 = new Paragraph("Timeline: ", color_black);
		paragraph6.add(new Chunk("    2 Weeks", color_normal));
		paragraph6.setSpacingAfter(30);
		document.add(paragraph6);

		Paragraph paragraph7 = new Paragraph("Note: ", color_red);
		paragraph7.add(new Chunk("             Note", color_normal_red));
		document.add(paragraph7);

		Paragraph paragraph8 = new Paragraph("Kind Regards", color_black);
		paragraph8.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph8);

		Paragraph paragraph9 = new Paragraph("John Doe", color_black);
		paragraph9.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph9);

		document.add(space);
		document.add(dottedLineSeparator);

		document.add(new Paragraph("\n"));

		Paragraph paragraph10 = new Paragraph("Thank you for the opportunity to quote on the above project", color_black);
		paragraph10.setAlignment(Element.ALIGN_MIDDLE);
		document.add(paragraph10);

	    // Closing the document
	    document.close();
	  }
		
}
