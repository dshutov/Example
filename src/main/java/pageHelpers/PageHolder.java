package pageHelpers;

import annotations.Page;
import pages.AbstractPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageHolder {

    private PageHolder(){

    }

    public static void putPageToCache(AbstractPage abstractPage){
        if (!pageCache.containsKey(abstractPage.getTitle())) {
            pageCache.put(abstractPage.getTitle(), abstractPage);
        }
    }

    public static AbstractPage getPageByName(String name) throws Exception {
        if (pageCache.containsKey(name)){
            return pageCache.get(name);
        } else {
            return createPageByName(name);
        }
    }

    public static String getPartialUrlByPageName(String name) throws Exception {
        return getAnnotationAttributesByPageName(name).partialUrl();
    }

    private static AbstractPage createPageByName(String pageName) throws Exception {
        return (AbstractPage) getClassByName(pageName).getConstructor().newInstance();
    }

    private static Page getAnnotationAttributesByPageName(String pageName) throws Exception {
        return (Page) getClassByName(pageName).getAnnotation(Page.class);
    }

    static Class getClassByName(String pageName) throws Exception {
        List<Class<?>> classList = ClassFinder.find(PAGE_PACKAGE);

        for (Class cClass : classList) {
            if(cClass.isAnnotationPresent(Page.class)) {
                Page page = (Page) cClass.getAnnotation(Page.class);
                if(page.name().equals(pageName)) {
                    return cClass;
                }
            }
        }
        throw new Exception("Page with name " + pageName + " can't be created, because class with this name is not found.");
    }


    private static Map<String, AbstractPage> pageCache = new HashMap<>();
    private static final String PAGE_PACKAGE = "pages";
}
