package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;

public class VacanciesPage {

    public static final By VACANCIES_PAGE_ROOT = By.xpath(".//*[contains(@class,'page-id-91')]");
    public static final By VACANCIES_MENU = By.id("menu-main-1");
    public static final By TEST_AUTOMATION_ENGINEER_ITEM = By.id("menu-item-3249");
    public static final By VACANCY_TAE_BLOCK = By.xpath(".//*[@data-href='https://ctco.lv/careers/vacancies/test-automation-engineer-2/' " +
            "and (contains(@class,'animated fadeIn active'))]");
    public static final By PARAGRAPH_SKILLS_AND_QUALIFICATION = By.xpath("//h1[text()='Test Automation Engineer']/following::p/em[text()='Professional skills and qualification:']/following::ul[1]");
    public static final By SKILLS_AND_QUALIFICATION_ITEM = By.xpath("li");
    private BaseFunc baseFunc;

    public VacanciesPage(BaseFunc baseFunc1){
        baseFunc = baseFunc1;
    }

    /**
     * Go to 'Test Automation Engineer' (TAE) vacancy page
     */
    public void openVacancyPage(){
        Assert.assertTrue("Vacancies page is not present", baseFunc.isPresentElement(VACANCIES_PAGE_ROOT));
        Assert.assertTrue("Don't see Test Automation Engineer vacancy in list",
                baseFunc.isPresentElementAndChild(VACANCIES_MENU, TEST_AUTOMATION_ENGINEER_ITEM));
        baseFunc.findElemWithDriver(VACANCIES_MENU).findElement(TEST_AUTOMATION_ENGINEER_ITEM).click();
        baseFunc.explicitWaitVisibilityOfElementLocated(5, VACANCY_TAE_BLOCK);
    }

    /**
     * Check count of items in the paragraph
     */
    public void checkParagraphAndCountOfItemsInIt(){
        Assert.assertTrue("Paragraph 'Professional skills and qualification:' is not visible",
                baseFunc.isPresentElement(PARAGRAPH_SKILLS_AND_QUALIFICATION));
        Assert.assertEquals("Error, paragraph 'Professional skills and qualification:' contains not 5 items", 5,
                baseFunc.getChildElementsCount(PARAGRAPH_SKILLS_AND_QUALIFICATION, SKILLS_AND_QUALIFICATION_ITEM));
    }
}
