package com.sample;

import java.util.ArrayList;
import java.util.List;

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
        	customer.set(ce1, "name", "Qingwen");
        	
        	List<Object> articles = new ArrayList<> ();
        	
        	FactType article = kbase.getFactType("com.sample", "Article");
        	
            Object article1 = article.newInstance();
            article.set(article1, "name", "结婚不如买房！");
            article.set(article1, "tag", 0);
            articles.add(article1);
            
            Object article2 = article.newInstance();
            article.set(article2, "name", "强行变道一时爽");
            article.set(article2, "tag", 1);
            articles.add(article2);
            
            Object article3 = article.newInstance();
            article.set(article3, "name", "这篇文章男人看了会沉默，女人看了会流泪");
            article.set(article3, "tag", 2);
            articles.add(article3);
            
            kSession.setGlobal("articles",articles);
            kSession.insert(ce1);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
