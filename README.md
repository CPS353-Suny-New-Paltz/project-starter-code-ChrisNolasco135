# Software Engineering Project Starter Code

This repo will start you off with an initial configuration that you'll modify as part of Checkpoint 1. As part of the modifications, you'll eventually delete the contents of this README and replace it with documentation for your project.

# Multi-Threaded Implementation

The multi-threaded implementation of the NetworkAPI (UserComputeAPI) uses a fixed thread pool to process compute jobs in parallel. The upper bound for the number of threads is **4**, as set in `MultiThreadedUserComputeImpl` using `Executors.newFixedThreadPool(4)`. This ensures efficient parallel processing while preventing resource exhaustion.

- The single-threaded implementation is still used for smoke tests.
- The multi-threaded implementation is exercised in `TestMultiUser`.

---

Checkpoint 2
This system will recieve an numerical input and decode the input utilizing Caesar's Cipher with a shift of 7, if given an input of 0124050508001608110523, it will result in Hello World 

![Image of System Diagram](https://github.com/CPS353-Suny-New-Paltz/project-starter-code-ChrisNolasco135/blob/main/images/SystemDiagram.jpg?raw=true)