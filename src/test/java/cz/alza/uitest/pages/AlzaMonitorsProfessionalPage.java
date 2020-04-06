package cz.alza.uitest.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AlzaMonitorsProfessionalPage extends AlzaPage<AlzaMonitorsProfessionalPage> implements AlzaInterface {

  private static final Logger logger = LogManager.getLogger("Test Logger");

  private static final String LEAST_EXPENSIVE = "//a[@href='#cenaasc']";
  private static final String ADD_CART_BTN = "//a[@class=\"btnk1\"]";
  private static final String PAGE_URL = ALZA_URL + "/profesionalni-monitory";
  private static final String BUYABLE_ITEM = String.format("//*[@id=\"boxes\"]/div[@class!='clear'][.%s]", ADD_CART_BTN);
  private static final String BUYABLE_ITEM_NAME = "//a[@class='name browsinglink']";


  @Override
  public AlzaMonitorsProfessionalPage openPage() {
    logger.info("Opening page");
    open(PAGE_URL);
    return this;
  }

  @Override
  public Boolean isCurrentPage() {
    return url().contains(PAGE_URL);
  }

  public String selectNthExpensive(Integer number) {
    logger.info("Selecting %d Most Expensive Items");
    ElementsCollection items = $$(By.xpath(BUYABLE_ITEM));
    String name = "";
    if(number <= items.size()) {
      SelenideElement e = items.get(number);
      name = e.find(By.xpath('.' + BUYABLE_ITEM_NAME)).text();
      e.find(By.xpath('.' + ADD_CART_BTN)).click();
    }
    return name;
  }

  public AlzaMonitorsProfessionalPage cheapestFirst() {
    logger.info("Sort by Most Expensive First");
    SelenideElement e = $(By.xpath(LEAST_EXPENSIVE));
    e.shouldBe(Condition.visible);
    e.click();
    return this;
  }
}