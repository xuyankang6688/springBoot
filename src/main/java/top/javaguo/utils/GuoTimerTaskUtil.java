package top.javaguo.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度
 * 
 * @author Guo
 * @date 2017/12/15
 */
public class GuoTimerTaskUtil {

	public static List<TimerTask> list = new ArrayList<TimerTask>();

	/**
	 * 
	 * 经过delay(ms)后开始进行调度，仅仅调度一次。
	 * 
	 * @param task
	 * 
	 * @param delay
	 *            延时时间(单位毫秒)
	 * 
	 */

	public static void schedule(TimerTask task, long delay)

	{

		Timer timer = new Timer();

		timer.schedule(task, delay);

		list.add(task);

	}

	/**
	 * 
	 * 在指定的时间点time上调度一次
	 * 
	 * @param task
	 * 
	 * @param time
	 *            定时开启时间
	 * 
	 */

	public static void schedule(TimerTask task, Date time)

	{

		Timer timer = new Timer();

		timer.schedule(task, time);

		list.add(task);

	}

	/**
	 * 
	 * 在delay（ms）后开始调度，每次调度完后，等待period（ms）后才开始调度
	 * 
	 * @param task
	 * 
	 * @param delay
	 *            延时时间(单位毫秒)
	 * 
	 * @param period
	 *            等待时间(单位毫秒)
	 * 
	 */

	public static void schedule(TimerTask task, long delay, long period)

	{

		Timer timer = new Timer();

		timer.schedule(task, delay, period);

		list.add(task);

	}

	/**
	 * 
	 * 在date（具体时间）后开始调度，每次调度完后，等待period（ms）后才开始调度
	 * 
	 * @param task
	 * 
	 * @param firstTime
	 * 
	 * @param period
	 * 
	 */

	public static void schedule(TimerTask task, Date firstTime, long period)

	{

		Timer timer = new Timer();

		timer.schedule(task, firstTime, period);

		list.add(task);

	}

	/**
	 * 
	 * 在delay(ms)后开始调度，然后每经过period(ms)再次调度
	 * 
	 * 与schedule(TimerTask task, long delay, long period)的区别：
	 * 
	 * 当schedule方法由于各种原因导致调用延迟后，依然会经过period（ms）去调用schedule方法
	 * 
	 * 当scheduleAtFixedRate方法由于各种原因导致调用延迟后，会根据原始时间叠加period（ms）计算出的列表进行调用
	 * 
	 * 例如：
	 * 
	 * schedule时间片是5s，那么理论上会在5、10、15、20这些时间片被调度，
	 * 
	 * 但是如果由于某些CPU征用导致未被调度，假如等到第8s才被第一次调度，
	 * 
	 * 那么schedule方法计算出来的下一次时间应该是第13s而不是第10s，
	 * 
	 * 这样有可能下次就越到20s后而被少调度一次或多次，
	 * 
	 * 而scheduleAtFixedRate方法就是每次理论计算出下一次需要调度的时间用以排序，
	 * 
	 * 若第8s被调度，那么计算出应该是第10s，所以它距离当前时间是2s，
	 * 
	 * 那么再调度队列排序中，会被优先调度，那么就尽量减少漏掉调度的情况。
	 * 
	 * @param task
	 * 
	 * @param delay
	 *            延时时间(单位毫秒)
	 * 
	 * @param period
	 *            等待时间(单位毫秒)
	 * 
	 */

	public static void scheduleAtFixedRate(TimerTask task, long delay, long period)

	{

		Timer timer = new Timer();

		timer.scheduleAtFixedRate(task, delay, period);

		list.add(task);

	}

	/**
	 * 
	 * 在date（具体时间）后开始调度，每次调度完后，等待period（ms）后才开始调度
	 * 
	 * 与schedule(TimerTask task, long delay, long period)的区别：
	 * 
	 * 当schedule方法由于各种原因导致调用延迟后，依然会经过period（ms）去调用schedule方法
	 * 
	 * 当scheduleAtFixedRate方法由于各种原因导致调用延迟后，会根据原始时间叠加period（ms）计算出的列表进行调用
	 * 
	 * 例如：
	 * 
	 * schedule时间片是5s，那么理论上会在5、10、15、20这些时间片被调度，
	 * 
	 * 但是如果由于某些CPU征用导致未被调度，假如等到第8s才被第一次调度，
	 * 
	 * 那么schedule方法计算出来的下一次时间应该是第13s而不是第10s，
	 * 
	 * 这样有可能下次就越到20s后而被少调度一次或多次，
	 * 
	 * 而scheduleAtFixedRate方法就是每次理论计算出下一次需要调度的时间用以排序，
	 * 
	 * 若第8s被调度，那么计算出应该是第10s，所以它距离当前时间是2s，
	 * 
	 * 那么再调度队列排序中，会被优先调度，那么就尽量减少漏掉调度的情况。
	 * 
	 * @param task
	 * 
	 * @param firstTime
	 * 
	 * @param period
	 * 
	 */

	public static void scheduleAtFixedRate(TimerTask task, Date firstTime, long period)

	{

		Timer timer = new Timer();

		timer.scheduleAtFixedRate(task, firstTime, period);

		list.add(task);

	}

	public static void cancelTimer(TimerTask task) {

		task.cancel();

		GuoTimerTaskUtil.list.remove(task);

	}

}