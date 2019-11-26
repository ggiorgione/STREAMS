package org.matsim.contrib.carsharing.js;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.matsim.contrib.carsharing.manager.PropertyManager;

import javax.script.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class JavaScriptCalculator <T>{

    private static final Logger logger = Logger.getLogger(JavaScriptCalculator.class);
    private CompiledScript compiledScript;
    private Invocable invocable;

    @Inject
    public JavaScriptCalculator(PropertyManager propertyManager) {
        Properties properties = propertyManager.getAppExaProperties();
        String script = readScript(properties.getProperty("jsFuncs.path"));
        compileScript(script);
    }

    private String readScript(String scriptPath) {
        try {
            logger.info("Reading script " + scriptPath);
            return new String(Files.readAllBytes(Paths.get(scriptPath)));
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }


    private void compileScript(String script){
        logger.info("Compiling script " + script);
        ScriptEngine engine = (new ScriptEngineManager()).getEngineByName("nashorn");
        Compilable compileEngine = (Compilable) engine;
        try{
            this.compiledScript = compileEngine.compile(script);
            this.compiledScript.eval();
            this.invocable = (Invocable) this.compiledScript.getEngine();
        }catch (ScriptException e){
            throw new RuntimeException(e);
        }
    }


    public final T calculate(String function, Object... args){
        try {
            return (T) this.invocable.invokeFunction(function, args);
        } catch (NoSuchMethodException | ScriptException e) {
            throw new RuntimeException(e);
        }
    }







}
