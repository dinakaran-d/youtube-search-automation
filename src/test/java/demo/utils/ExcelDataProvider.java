package demo.utils;

import org.testng.annotations.DataProvider;
import java.nio.file.Paths;

public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public static Object[][] excelData() {
        String fileLocation = Paths.get("src", "test", "resources", "data.xlsx").toAbsolutePath().toString();
        //String fileLocation = System.getProperty("user.dir")+"/src/test/resources/data.xlsx";
        System.out.println("Fetching excel file from "+fileLocation);
        return ExcelReaderUtil.readExcelData(fileLocation);
    }
    public static void main(String args[]){
        excelData();
    }
}