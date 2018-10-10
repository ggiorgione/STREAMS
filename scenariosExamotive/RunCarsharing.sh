#!/bin/bash                                                                                                                                                                                                        
rm -rf test
java -Xmx2G -cp /home/lbolzani/MatSIMWorkspace/exa-matsim-carsharing/target/exa-matsim-carsharing-1.0-SNAPSHOT-jar-with-dependencies.jar org.matsim.contrib.carsharing.runExample.RunCarsharing /home/lbolzani/MatSIMWorkspace/exa-matsim-carsharing/scenariosExamotive/ config.xml config-examotive.properties http://localhost:8086
