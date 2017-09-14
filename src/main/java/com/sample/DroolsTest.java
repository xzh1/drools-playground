package com.sample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	KieBase kbase = kSession.getKieBase();
        	
        	// go !
        	FactType customer = kbase.getFactType("com.sample", "Customer");
        	Object ce1 = customer.newInstance();
        	customer.set(ce1, "id", 123L);
        	customer.set(ce1, "age", 19);
        	customer.set(ce1, "firstName", "Pendragon");
        	customer.set(ce1, "lastName", "Artoria");
        	
        	Map<String, Object> stackholders = new HashMap<String, Object> ();
        	
        	Object wife = customer.newInstance();
        	customer.set(wife, "id", 124L);
        	customer.set(wife, "age", 18);
        	customer.set(wife, "firstName", "Guenevere");
        	customer.set(wife, "lastName", "Arthur");
        	stackholders.put("wife", wife);
        	
        	Object son = customer.newInstance();
        	customer.set(son, "id", 125L);
        	customer.set(son, "age", 12);
        	customer.set(son, "firstName", "Mordred");
        	customer.set(son, "lastName", "Arthur");
        	stackholders.put("son", son);
        	
        	customer.set(ce1, "stakeholders", stackholders);
        	
        	List<Object> subscriptions = new ArrayList<> ();
        	
        	FactType subscription = kbase.getFactType("com.sample", "Subscription");
        	
        	Object sub1 = subscription.newInstance();
        	subscription.set(sub1, "id", 1111L);
        	subscription.set(sub1, "channel", "web");
        	subscriptions.add(sub1);
        	
        	Object sub2 = subscription.newInstance();
        	subscription.set(sub2, "id", 2222L);
        	subscription.set(sub2, "channel", "phone");
        	subscriptions.add(sub2);
        	
        	Object sub3 = subscription.newInstance();
        	subscription.set(sub3, "id", 3333L);
        	subscription.set(sub3, "channel", "post");
        	subscriptions.add(sub3);
        	
        	customer.set(ce1, "subscriptions", subscriptions);
        	
        	List<Object> articles = new ArrayList<> ();
        	
        	FactType article = kbase.getFactType("com.sample", "Article");
        	
            Object article1 = article.newInstance();
            article.set(article1, "name", "\"The fight between father and son\"");
            article.set(article1, "tag", 0);
            articles.add(article1);
            
            Object article2 = article.newInstance();
            article.set(article2, "name", "\"How to wield Excalibur\"");
            article.set(article2, "tag", 1);
            articles.add(article2);
            
            Object article3 = article.newInstance();
            article.set(article3, "name", "\"How to build the table\"");
            article.set(article3, "tag", 2);
            articles.add(article3);
            
            kSession.setGlobal("articles",articles);
            kSession.setGlobal("customer",ce1);
            //kSession.insert(ce1);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
