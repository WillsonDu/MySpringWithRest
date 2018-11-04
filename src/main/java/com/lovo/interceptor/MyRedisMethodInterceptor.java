package com.lovo.interceptor;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * ��Ҫ�����ڻ��ʵ��(��redis���)
 * 
 * @author Administrator
 *
 */
public class MyRedisMethodInterceptor implements MethodInterceptor {

	private RedisTemplate<Serializable, Object> redisTemplate;

	private Long defaultCacheExpireTime = 30L; // 过期时间默认30s

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object value = null;

		final String targetName = invocation.getThis().getClass().getName();
		final String methodName = invocation.getMethod().getName();

		final Object[] arguments = invocation.getArguments();

		final String key = getCacheKey(targetName, methodName, arguments);

		try {
			if (exists(key)) {
				System.out.println("从缓存中读取...key为: " + key);
				return getCache(key);
			}

			System.out.println("从数据库读取...");
			value = invocation.proceed();
			if (value != null) {
				final String tkey = key;
				final Object tvalue = value;
				new Thread(new Runnable() {
					@Override
					public void run() {
						setCache(tkey, tvalue, defaultCacheExpireTime);
					}
				}).start();
			}
			return value;

		} catch (final Exception e) {
			e.printStackTrace();
			// redis服务发生异常，直接跳过之，从数据库读取
			if (value == null) {
				return invocation.proceed();
			}
		}

		return null;
	}

	private String getCacheKey(String targetName, String methodName, Object[] arguments) {

		final StringBuffer sb = new StringBuffer();
		sb.append(targetName).append("_").append(methodName);
		if (arguments == null || arguments.length == 0) {
			return sb.toString();
		}

		for (final Object argument : arguments) {
			sb.append("_").append(argument);
		}
		return sb.toString();
	}

	private boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	private Object getCache(final String key) {
		final ValueOperations<Serializable, Object> opsForValue = redisTemplate.opsForValue();
		return opsForValue.get(key);
	}

	private boolean setCache(final String key, final Object value, final long expireTime) {
		boolean flag = false;
		try {
			final ValueOperations<Serializable, Object> opsForValue = redisTemplate.opsForValue();
			opsForValue.set(key, value, expireTime, TimeUnit.SECONDS);
			flag = true;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public RedisTemplate<Serializable, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
