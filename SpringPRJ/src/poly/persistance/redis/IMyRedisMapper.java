package poly.persistance.redis;

public interface IMyRedisMapper {

	public void doSaveData() throws Exception;
	
	public void doSaveDataforList() throws Exception;
	
	public void doSaveDataforListJSON() throws Exception;
	
	public void doSaveDataforHashTable() throws Exception;
	
	public void doSaveDataforSet() throws Exception;
	
	public void doSaveDataforZSet() throws Exception;
}

