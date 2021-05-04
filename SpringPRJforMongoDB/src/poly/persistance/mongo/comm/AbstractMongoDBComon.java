package poly.persistance.mongo.comm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.model.Indexes;

public abstract class AbstractMongoDBComon {

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private MongoTemplate mongodb;

	protected boolean createCollection(String colNm) throws Exception {
		return createCollection(colNm, "");
	}

	protected boolean createCollection(String colNm, String index) throws Exception {

		String[] indexArr = { index };
		
		return createCollection(colNm, indexArr);
	}

	/**
	 * 인덱스 컬럼이 여러개일때 컬렉션 생성
	 * 
	 * @param 생성할 컬렉션명
	 * @param 생성할 인덱스
	 * @return 생성결과
	 */
	protected boolean createCollection(String colNm, String[] index) throws Exception {

		log.info(this.getClass().getName() + ".createCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 컬렉션이 없는 경우 생성함
		if (!mongodb.collectionExists(colNm)) {

			// 인덱스 값이 존재한다면..
			if (index.length > 0) {

				// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
				// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
				mongodb.createCollection(colNm).createIndex(Indexes.ascending(index));

			} else {

				// 인덱스가 없으면 인덱스 없이 컬렉션 생성
				mongodb.createCollection(colNm);

			}

			res = true;
		}

		log.info(this.getClass().getName() + ".createCollection End!");

		return res;

	}

}


