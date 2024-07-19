package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ExportToExcel {
    public static void generate_excel_report() {
        // Step 1: Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Step 2: Establish a connection to the database
        try {
            Properties property = PropertyFileReader.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/BimabharosaDB_Details.properties");

            String url = property.getProperty("DB_Endpoint");
            String username = property.getProperty("Username");
            String password = property.getProperty("Password");

            Connection connection = DriverManager.getConnection(url, username, password);

            // Step 3: Execute a SELECT query
            String sql = "SELECT * FROM(SELECT SL_NO,MODULE,TC_NAME,REQ,RES,ERR_CODE,TEST_STATUS,REMARKS,CREATED_AT,ENTITY_REF_NO FROM LOG_HISTORY " +
                    "ORDER BY CREATED_AT DESC " +
                    "LIMIT 0," + BimabharosaDatabaseHelper.count + ") AS SUBQUERY ORDER BY SL_NO;";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                // Step 4: Retrieve the query results and write data to a CSV file
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
                String timeStamp = dateFormat.format(new Date());
                String filename = System.getProperty("user.dir") + "/Excel_Report/Report" + timeStamp + ".xlsx";
                try {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("Test_Details");

                    // Write column headers
                    Row headerRow = sheet.createRow(0);
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        Cell cell = headerRow.createCell(i - 1);

                        // Create a cell style for bold text
                        CellStyle style = workbook.createCellStyle();
                        Font boldFont = workbook.createFont();
                        boldFont.setBold(true);
                        style.setFont(boldFont);

                        // Create a cell style for background color
                        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                        // Create a cell style for boaders
                        style.setBorderTop(BorderStyle.THIN);
                        style.setBorderBottom(BorderStyle.THIN);
                        style.setBorderLeft(BorderStyle.THIN);
                        style.setBorderRight(BorderStyle.THIN);

                        //set cell value
                        cell.setCellValue(resultSet.getMetaData().getColumnName(i));
                        //set cell style
                        cell.setCellStyle(style);
                    }

                    // Write data rows
                    int rowNum = 1;
                    while (resultSet.next()) {
                        Row dataRow = sheet.createRow(rowNum);
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                            Cell cell = dataRow.createCell(i - 1);
                            CellStyle borderStyle = workbook.createCellStyle();
                            borderStyle.setBorderTop(BorderStyle.THIN);
                            borderStyle.setBorderBottom(BorderStyle.THIN);
                            borderStyle.setBorderLeft(BorderStyle.THIN);
                            borderStyle.setBorderRight(BorderStyle.THIN);
                            cell.setCellValue(resultSet.getString(i));
                            cell.setCellStyle(borderStyle);

                        }
                        rowNum++;
                    }

                    // Write the workbook to a file
                    try (FileOutputStream fileOut = new FileOutputStream(filename)) {
                        workbook.write(fileOut);
                        System.out.println("Data exported to Excel successfully.");
                    }

                    //AutoMailHelper.sendAutoMail(timeStamp);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
