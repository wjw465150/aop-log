/* aop-log的线程池配置类
 * author: @wjw
 * date:   2021年10月17日 下午12:46:14
 * note: 
 */
package com.github;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * aop-log的线程池配置类 The Class ThreadPoolProperties.
 */
@ConfigurationProperties(prefix = "aop-log.thread-pool")
public class ThreadPoolProperties {
  
  /** The core pool size. */
  private int corePoolSize  = 5;
  
  /** The max pool size. */
  private int maxPoolSize   = 20;
  
  /** The queue capacity. */
  private int queueCapacity = 256;
  
  /**
   * Gets the core pool size.
   *
   * @return the core pool size
   */
  public int getCorePoolSize() {
    return corePoolSize;
  }
  
  /**
   * Sets the core pool size.
   *
   * @param corePoolSize the new core pool size
   */
  public void setCorePoolSize(int corePoolSize) {
    this.corePoolSize = corePoolSize;
  }
  
  /**
   * Gets the max pool size.
   *
   * @return the max pool size
   */
  public int getMaxPoolSize() {
    return maxPoolSize;
  }
  
  /**
   * Sets the max pool size.
   *
   * @param maxPoolSize the new max pool size
   */
  public void setMaxPoolSize(int maxPoolSize) {
    this.maxPoolSize = maxPoolSize;
  }
  
  /**
   * Gets the queue capacity.
   *
   * @return the queue capacity
   */
  public int getQueueCapacity() {
    return queueCapacity;
  }
  
  /**
   * Sets the queue capacity.
   *
   * @param queueCapacity the new queue capacity
   */
  public void setQueueCapacity(int queueCapacity) {
    this.queueCapacity = queueCapacity;
  }
  
  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "ThreadPoolProperties [corePoolSize=" + corePoolSize + ", maxPoolSize=" + maxPoolSize + ", queueCapacity=" + queueCapacity + "]";
  }
  
}
