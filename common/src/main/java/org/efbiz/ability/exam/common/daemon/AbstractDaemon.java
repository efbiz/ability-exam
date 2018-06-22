package org.efbiz.ability.exam.common.daemon;

import org.apache.commons.daemon.Daemon;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public abstract class AbstractDaemon implements Daemon {

  protected AnnotationConfigApplicationContext context;
  protected Thread workThread;
  private boolean shutdownRequested = false;

  protected abstract void run();

  protected Thread getWorkThread() {
    return workThread;
  }

  protected void createWorkThread() {
    final AbstractDaemon instance = this;
    workThread = new Thread() {
      @Override
      public synchronized void start() {
        super.start();
      }

      @Override
      public void run() {
        instance.run();
      }
    };
  }

  protected boolean isShutdownRequested() {
    return shutdownRequested;
  }

  /**
   * @param shutdown flag to set
   */
  protected void setShutdownRequested(boolean shutdownRequested) {
    this.shutdownRequested = shutdownRequested;
  }

  @Override
  public void destroy() {
    if (context != null) {
      context.close();
    }
    workThread = null;
  }

}
