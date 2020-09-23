package com.excel.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.excel.app.model.Employee;

public class ExcelHelper {
	private static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	private static String SHEETNAME = "Sheet1";
	public static boolean isExcelFormat(MultipartFile file) {

		if (TYPE.equals(file.getContentType())) {
			return true;
		}

		return false;
	}
	
	public static List<Employee> excelToEmployee(InputStream is) throws IOException{
		List<Employee> employees = new ArrayList<Employee>();
		Workbook workbook = new XSSFWorkbook(is);
		Sheet sheet = workbook.getSheet(SHEETNAME);
		Iterator<Row> rows = sheet.iterator();
		int rowNumber = 0;
		while(rows.hasNext()) {
			Row currentRow= rows.next();
			if(rowNumber == 0) {
				rowNumber++;
				continue;
			}
			Iterator<Cell> cells = currentRow.iterator();
			Employee employee = new Employee();
			int cellId=0;
			while(cells.hasNext()) {
				Cell currentCell = cells.next();
				switch(cellId) {
				case 0 : 
					employee.setEmpNo(currentCell.getStringCellValue());
					break;
				case 1 :
					employee.setEmpName(currentCell.getStringCellValue());
					break;
				case 2 : 
					employee.setSalary(currentCell.getNumericCellValue());
					break;
				default : 
					break;
				}
				cellId++;
			}
			employees.add(employee);
		}
		workbook.close();
		return employees;
	}

}
