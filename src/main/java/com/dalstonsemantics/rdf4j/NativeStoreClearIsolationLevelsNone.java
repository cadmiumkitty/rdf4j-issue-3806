package com.dalstonsemantics.rdf4j;

import java.io.File;

import org.eclipse.rdf4j.IsolationLevels;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.nativerdf.NativeStore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NativeStoreClearIsolationLevelsNone {
    
    public static void main(String args[]) throws Exception {

        File data = new File("data");
        NativeStore store = new NativeStore(data, "spoc,opsc,ospc");
        store.setForceSync(false);
        store.setNamespaceCacheSize(10000);
        store.setNamespaceIDCacheSize(10000);
        store.setValueCacheSize(10000000);
        store.setValueIDCacheSize(10000000);
        Repository repo = new SailRepository(store);

        RepositoryConnection connection = repo.getConnection();

        ValueFactory vf = connection.getValueFactory();

        IRI c1 = vf.createIRI("http://example.com/context");

        long start = System.currentTimeMillis();
        log.info("Calling connection.clear().");

        connection.begin(IsolationLevels.NONE);

        connection.clear(c1);

        connection.commit();

        long finish = System.currentTimeMillis();
        log.info("Completed connection.clear(). Duration: {}", finish - start);
    }
}
