package impl;

import java.io.InputStream;

import org.drools.core.io.impl.DescrResource;
import org.drools.core.io.impl.ResourceFactoryServiceImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import impl.models.Message;

public class Main {
	
	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		
		KieSession ksession = kc.newKieSession("HelloWorldKS");
		
		final Message message = new Message();
        message.setMsgtext( "Hello World first try" );
        message.setType( "Hello" );
        ksession.insert( message );
        
        ksession.fireAllRules();
        
        ksession.dispose();
	}

}
