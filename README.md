# Test code to reproduce Rdf4j Issue 3806

Issue: https://github.com/eclipse/rdf4j/issues/3806

## Timing clear() call

 1. Clear contents of `./data`
 1. Run `NativeStoreAdd`
 1. Start Docker container with Docker Compose (default _JAVA_OPTIONS include JProfiler agent)
 1. Connect to profiler

## Initial results without Docker volumes

 1. Observe the results (for 10K records takes just under 20 seconds in the container versus just under a 1 second directly outside Docker):

 ![CPU profiling of connection.clear() call](profiler.png)

## Results with Docker volumes

Fairly significant speed up:

```
[main] INFO com.dalstonsemantics.rdf4j.NativeStoreAdd - Creating statements.
[main] INFO com.dalstonsemantics.rdf4j.NativeStoreAdd - Statements created. Duration: 999
[main] INFO com.dalstonsemantics.rdf4j.NativeStoreClear - Calling connection.clear().
[main] INFO com.dalstonsemantics.rdf4j.NativeStoreClear - Completed connection.clear(). Duration: 395
```