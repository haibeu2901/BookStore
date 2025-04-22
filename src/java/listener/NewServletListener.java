/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author beu29
 */
public class NewServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Deploying.....");
        loadSiteMap(context);
        context.log("Load site-map success!!!");
        loadAuthenticationFile(context);
        context.log("Load authentication-map success!!!");
        context.log("Deployed!!!!!");
    }
    
    private void loadSiteMap(ServletContext context) {
        String siteMapLocation = context.getInitParameter("SITE_MAP_LOCATION");
        InputStream is = null;
        if (siteMapLocation != null) {
            Properties properties = new Properties();
            is = context.getResourceAsStream(siteMapLocation);
            try {
                properties.load(is);
                context.setAttribute("SITE_MAP", properties);
            } catch (IOException ex) {
                context.log("NewServletListener_IO: " + ex.getMessage());
            }
        }//end siteMapLocation is existed
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Undeploying.....");
    }
}
