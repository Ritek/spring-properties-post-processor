# Spring properties post processor example

## What is it
This is a simple project that shows how to use spring properties post processor.

## Why is it useful
A spring post processor allows loading and transforming text files into environment from places other that resources.  
This is particularly useful in an automated environment like a CICD pipeline. Storing keys locally or having them committed in the repository is dangerous.  
Instead the necessary files can be downloaded beforehand or can be accessed from network drive and loaded safely into environment.

## How does it work
1. A file `spring.facotiries` has to be present in `resource` directory with classpath to post processor.  
2. In `application.yml` a path has to be given so that Spring knows where to look for the file. 
3. A post processor class has to implement `EnvironmentPostProcessor` and override `postProcessEnvironment` function.