package poly.persistance.mongo;

public interface IMongoTestMapper {

	/**
	 * MongoDB 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 * */
	public boolean createCollection(String colNm) throws Exception;

}
