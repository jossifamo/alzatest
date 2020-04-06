package cz.alza.uitest.tests;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;

public class DownloadTest {
    public void downloadByXpathTest(){
        try {
            $("div").download();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
