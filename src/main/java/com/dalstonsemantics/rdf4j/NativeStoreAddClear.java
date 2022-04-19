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
public class NativeStoreAddClear {
    
    public static void main(String args[]) throws Exception {
        NativeStoreAdd.main(args);
        NativeStoreClear.main(args);
    }
}
