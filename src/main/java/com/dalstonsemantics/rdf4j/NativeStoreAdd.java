package com.dalstonsemantics.rdf4j;

import java.io.File;

import org.eclipse.rdf4j.IsolationLevels;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.nativerdf.NativeStore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NativeStoreAdd {
    
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

        IRI p = RDFS.LABEL;
        Literal o = vf.createLiteral("Some test resource");
        IRI c = vf.createIRI("http://example.com/context");
        
        long start = System.currentTimeMillis();
        log.info("Creating statements.");

        connection.begin(IsolationLevels.NONE);

        for (int i = 0; i < 10000; i++) {
            IRI s = vf.createIRI("http://example.com/%d".formatted(i));
            connection.add(s, p, o, c);
        }

        connection.commit();

        long finish = System.currentTimeMillis();
        log.info("Statements created. Duration: {}", finish-start);
    }
}
