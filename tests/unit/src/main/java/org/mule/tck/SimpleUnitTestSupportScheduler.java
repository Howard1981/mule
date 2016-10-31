/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.tck;

import org.mule.runtime.core.api.scheduler.Scheduler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * {@link Scheduler} implementation to be used in unit tests. Provides a thread pool with just 2 threads.
 * <p>
 * The size of the pool is 2 because some tests may dispatch 2 independent tasks that have to synchronize on each other. Having
 * only one pool may cause one task to wait on another that will never start.
 *
 * @since 4.0
 */
public class SimpleUnitTestSupportScheduler extends ScheduledThreadPoolExecutor implements Scheduler {

  public SimpleUnitTestSupportScheduler(int corePoolSize,
                                        ThreadFactory threadFactory,
                                        RejectedExecutionHandler handler) {
    super(corePoolSize, threadFactory, handler);
  }

  @Override
  public void stop(long gracefulShutdownTimeoutSecs, TimeUnit unit) {
    // Nothing to do. The lifecycle of this pool is managed by the UnitTestSchedulerService that instantiated this
  }

}
