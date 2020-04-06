package cz.alza.uitest.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

abstract public class AlzaPage<T> {

  static final String ALZA_URL = "https://www.alza.cz";

  public static final String NBSP = "\u00a0";

  private static final Logger logger = LogManager.getLogger("Test Logger");

  Language currentLanguage = Language.CZ_CZ;

  private static final String CART_LINK = "//*[@id=\"basketc\"]";
  private static final String CART = CART_LINK + "//span[@class='count']";
  private static final String LANGUAGE_MENU = "//*[@id='languageSwitch']";
  private static final String LANGUAGE_OPTION = "//*[@id='languageList']/div/a[normalize-space(text())='%s']";
  private static final String SEARCH_FIELD = "//*[@id=\"edtSearch\"]";
  private static final String SEARCH_BUTTON = "//*[@id=\"btnSearch\"]";

  public enum Category
  {
    LAPTOPS("Notebooky"),
    PROFESSIONAL("Profesionální"),
    MONITORS("Monitory");

    private String cat;

    Category(String cat) {
      this.cat = cat;
    }

    public String getCategory() {
      return cat;
    }
  }

  public enum Language
  {
    CZ_CZ("Alza.cz", "", "/?lang=CZ"),
    CZ_ENG("Alza.cz - English", "/EN/", "/?lang=EN");

    private String altPath;
    private String path;
    private String lang;

    Language(String lang, String path, String altPath) {
      this.lang = lang;
      this.path = path;
      this.altPath = altPath;
    }

    public String getText() {
      return lang;
    }
    public String getPath() {
      return path;
    }
    public String getAltPath() {
      return altPath;
    }

  }

  public com.codeborne.selenide.SelenideElement cartElement() {
    return $(org.openqa.selenium.By.xpath(CART));
  }

  public AlzaCartPage goToCart() {
    logger.info("Going to cart");
    $x(CART_LINK).click();
    return new AlzaCartPage();
  }

  private void setCurrentLanguage(Language lang) {
    currentLanguage = lang;
  }

  public T changeLanguage(Language lang){
    $x(LANGUAGE_MENU).click();
    $x(String.format(LANGUAGE_OPTION, lang.getText())).click();
    setCurrentLanguage(lang);
    @SuppressWarnings("unchecked")
    T childThatMethodWasExecutedOn = (T) this;
    return childThatMethodWasExecutedOn;
  }

  public void search(String searchText) {
    $x(SEARCH_FIELD).val(searchText);
    $x(SEARCH_BUTTON).click();
  }
}