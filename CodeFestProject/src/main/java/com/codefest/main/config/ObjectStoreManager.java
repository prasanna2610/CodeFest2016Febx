package com.codefest.main.config;

public final class ObjectStoreManager

{
	private static ObjectStoreManager c_instance = new ObjectStoreManager();
	@SuppressWarnings("rawtypes")
	private ThreadLocal m_sessionStore = new ThreadLocal();

	public static ObjectStoreManager getInstance() {
		return c_instance;
	}

	@SuppressWarnings("unchecked")
	public void setSessionLevelObjectStore(Object store) {
		this.m_sessionStore.set(store);
	}

	public Object getSessionLevelObjectStore() {
		return (Object) this.m_sessionStore.get();
	}

	public void cleanup() {
		setSessionLevelObjectStore(null);
	}

}
