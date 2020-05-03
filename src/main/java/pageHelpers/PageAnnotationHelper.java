package pageHelpers;

import annotations.Element;
import annotations.Page;
import org.openqa.selenium.WebElement;
import pages.AbstractPage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static pageHelpers.PageHolder.getClassByName;


public class PageAnnotationHelper {

    public PageAnnotationHelper(AbstractPage newPageClass) throws Exception {
        this.pageClass = newPageClass;
        this.page = getPageAnnotation();
        this.elementList = getAllDeclaredElementsOnThePage();
    }

    public List<WebElement> getElementList() {
        return elementList;
    }

    public String getPageTitle() { return page.title(); }

    private Page getPageAnnotation() throws Exception {
        if(pageClass.getClass().isAnnotationPresent(Page.class)){
            return (Page) pageClass.getClass().getAnnotation(Page.class);
        } else {
            throw new Exception("Annotation 'Page' is not found in class " + pageClass);
        }
    }


    public WebElement getElementByAnnotation(String annotationName) throws Exception {
        WebElement element = null;
        for (Field f : pageClass.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Element.class)&& f.getAnnotation(Element.class).name().equals(annotationName)) {
                f.setAccessible(true);
                element = (WebElement) f.get(pageClass);
            }
        }
        if(element == null) {
            throw new Exception(String.format("There are no elements with such annotation '%s' on the page ", annotationName));
        }
        return element;
    }

    private List<WebElement> getAllDeclaredElementsOnThePage() throws Exception {
        List<WebElement> elements = new ArrayList<>();
        Class cClass = getClassByName( getPageAnnotation().name());
        for (Field f : cClass.getDeclaredFields()) {
            if (f.isAnnotationPresent(Element.class)) {
                f.setAccessible(true);
                elements.add((WebElement) f.get(pageClass));
            }
        }
        return elements;
    }


    private AbstractPage pageClass;
    private Page page;
    private List<WebElement> elementList;
}
