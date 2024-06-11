package gnu.java.awt;

import gnu.classpath.debug.SystemLogger;

import java.awt.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugLogger {
  private static DebugLogger instance;
  private static Logger logger;

  private DebugLogger() {
    try {
      logger = SystemLogger.getLogger(AlphaComposite.class.getName());
//      FileHandler fh = new FileHandler("logs/AlphaCompositeUnitTests.%u.log", false);
//      addHandler(fh);
      logger.setLevel(Level.INFO);
    } catch (Exception e) {
      System.out.println("No se pudo configurar el logger");
    }
  }

  public synchronized static DebugLogger getInstance() {
    if (instance == null) {
      instance = new DebugLogger();
    }
    return instance;
  }

  public synchronized Logger getLogger() {
    return logger;
  }

  public synchronized void logStackTrace(Thread currentThread) {
    String sb = getStackTrace(currentThread);
    logger.info(sb.toString());
  }

  public synchronized String getStackTrace(Thread currentThread) {
    StackTraceElement[] elements = currentThread.getStackTrace();
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < elements.length; i++) {
      StackTraceElement element = elements[i];
      sb.append("\tat " + element.getClassName() + "." + element.getMethodName() + "(" + element.getFileName() + ":" + element.getLineNumber() + ")\n");
    }
    return sb.toString();
  }
}
