import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.InputStreamReader;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class FilesTests {
    private ClassLoader cl = FilesTests.class.getClassLoader();

    @DisplayName("Пооверка содержимого CSV в ZIP архиве")
    @Test
    void checkCvsFileInZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("zip.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> csvContent = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[]{"CSV;File"}, csvContent.get(1));
                    break;

                }
            }
        }
    }

    @DisplayName("Проверка содержимого XLSX в ZIP архиве")
    @Test
    void checkExcelFileInZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("zip.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals(xls.excel.getSheetAt(0).getRow(0).getCell(0)
                            .getStringCellValue(), "This is XLSX");
                    break;
                }
            }
        }
    }

    @DisplayName("Проверка соержимого PDF в ZIP архиве")
    @Test
    void checkPdfFileInZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("zip.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.text).contains("This is PDF");
                    break;
                }
            }
        }
    }
}