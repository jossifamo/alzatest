package cz.alza.uitest.pages;

import com.codeborne.selenide.Condition;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AlzaLandingPage extends AlzaPage<AlzaLandingPage> implements AlzaInterface {

  private static final Logger logger = LogManager.getLogger("Test Logger");

  private static final String CATEGORY = "//ul[@class='fmenu']//div[@class='bx']/a[normalize-space(text())='%s']";
  private static final String SUBCATEGORY = "//div[@class='float-block'][.//a[@class='head'][normalize-space(text())='%s']]";

  @Override
  public AlzaLandingPage openPage() {
    logger.info("Opening page");
    open(ALZA_URL);
    return this;
  }

  @Override
  public Boolean isCurrentPage() {
    return url().equals(ALZA_URL + this.currentLanguage.getPath()) || url().equals(ALZA_URL + this.currentLanguage.getAltPath());
  }

  public AlzaLandingPage selectCategory(Category category, Category subCategory) {
    logger.info(String.format("Selecting category: %s > %s", category.getCategory(), subCategory.getCategory()));
    $(By.xpath(String.format(CATEGORY, category.getCategory()))).hover();
    $(By.xpath(String.format(SUBCATEGORY, subCategory.getCategory()))).shouldBe(Condition.visible).click();
    return this;
  }

}