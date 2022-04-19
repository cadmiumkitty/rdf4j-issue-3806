# Test code to reproduce Rdf4j Issue 3806

Issue: https://github.com/eclipse/rdf4j/issues/3806

## Timing clear call

 1. Clear contents of `./data`
 1. Run `NativeStoreAdd`
 1. Start Docker container with Docker compose (default _JAVA_OPTIONS include JProfiler agent)
 1. Connect to profiler
 1. Observe the results (for 10K records takes just under 20 seconds in the container versus just under a 1 second directly outside Docker):

 ![CPU profiling of connection.clear() call](profiler.png)