package cz.alza.uitest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AlzaCartPage extends AlzaPage<AlzaCartPage> implements AlzaInterface {

  private static final String REMOVE_BUTTON = "//div[contains(@class,'del alzaico-f-cross')]";
  private static final String PAGE_URL = ALZA_URL + "/Order1.htm";
  private static final String CART_ITEM = "//div[@class='nameContainer']/a[@class='mainItem']";

  @Override
  public AlzaCartPage openPage() {
    open(PAGE_URL);
    return this;
  }

  @Override
  public Boolean isCurrentPage() {
    return url().contains(PAGE_URL);
  }

  public AlzaCartPage cleanCart() {
    $$(By.xpath(REMOVE_BUTTON)).forEach(SelenideElement::click);
    return this;
  }

  public SelenideElement getFirstCartItem() {
    return $x(CART_ITEM);
  }

}