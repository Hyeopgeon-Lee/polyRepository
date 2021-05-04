package poly.persistance.mongo.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import poly.persistance.mongo.IMelonMapper;
import poly.persistance.mongo.comm.AbstractMongoDBComon;
import poly.util.CmmUtil;

@Component("MelonMapper")
public class MelonMapper extends AbstractMongoDBComon implements IMelonMapper {

	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int insertSong(List<Map<String, Object>> pList, String colNm) throws Exception {

		log.info(this.getClass().getName() + ".insertSong Start!");

		int res = 0;

		if (pList == null) {
			pList = new LinkedList<Map<String, Object>>();
		}

		// 데이터를 저장할 컬렉션 생성
		super.createCollection(colNm, "collectTime");

		// 저장할 컬렉션 객체 생성
		MongoCollection<Document> col = mongodb.getCollection(colNm);

		Iterator<Map<String, Object>> it = pList.iterator();

		while (it.hasNext()) {
			Map<String, Object> pMap = it.next();

			if (pMap == null) {
				pMap = new LinkedHashMap<String, Object>();
			}

			// 레코드 한개씩 저장하기
			col.insertOne(new Document(pMap));

		}

		col = null;

		res = 1;

		log.info(this.getClass().getName() + ".insertSong End!");

		return res;
	}

	@Override
	public List<Map<String, String>> getSongList(String colNm) throws Exception {

		log.info(this.getClass().getName() + ".getSongList Start!");

		// 조회 결과를 전달하기 위한 객체 생성하기
		List<Map<String, String>> rList = new LinkedList<Map<String, String>>();

		MongoCollection<Document> col = mongodb.getCollection(colNm);

		// 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
		Document projection = new Document();
		projection.append("song", "$song");
		projection.append("singer", "$singer");

		// MongoDB는 무조건 ObjectId가 자동생성되며, ObjectID는 사용하지 않을때, 조회할 필요가 없음
		// ObjectId를 가지고 오지 않을 때 사용함
		projection.append("_id", 0);

		// MongoDB의 find 명령어를 통해 조회할 경우 사용함
		// 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
		FindIterable<Document> rs = col.find(new Document()).projection(projection);

		// 저장 결과를 제어가능한 구조인 Iterator로 변경하기 위해 사용함
		Iterator<Document> cursor = rs.iterator();

		while (cursor.hasNext()) {
			Document doc = cursor.next();

			if (doc == null) {
				doc = new Document();

			}

			// 조회 잘되나 출력해 봄
			String song = CmmUtil.nvl(doc.getString("song"));
			String singer = CmmUtil.nvl(doc.getString("singer"));

			log.info("song : " + song);
			log.info("singer : " + singer);

			Map<String, String> rMap = new LinkedHashMap<String, String>();

			rMap.put("song", song);
			rMap.put("singer", singer);

			// 레코드 결과를 List에 저장하기
			rList.add(rMap);

			rMap = null;
			doc = null;
		}

		// 사용이 완료된 객체는 메모리에서 강제로 비우기
		cursor = null;
		rs = null;
		col = null;

		projection = null;

		log.info(this.getClass().getName() + ".getSongList End!");

		return rList;
	}

	@Override
	public List<Map<String, Object>> getSingerSongCnt(String colNm) throws Exception {

		log.info(this.getClass().getName() + ".getSingerSongCnt Start!");

		// 조회 결과를 전달하기 위한 객체 생성하기
		List<Map<String, Object>> rList = new LinkedList<Map<String, Object>>();

		// MongoDB 조회 쿼리
		List<? extends Bson> pipeline = Arrays.asList(
				new Document().append("$group",
						new Document().append("_id", new Document().append("singer", "$singer")).append("COUNT(singer)",
								new Document().append("$sum", 1))),
				new Document()
						.append("$project",
								new Document().append("singer", "$_id.singer").append("singerCnt", "$COUNT(singer)")
										.append("_id", 0)),
				new Document().append("$sort", new Document().append("singerCnt", 1)));

		MongoCollection<Document> col = mongodb.getCollection(colNm);
		AggregateIterable<Document> rs = col.aggregate(pipeline).allowDiskUse(true);
		Iterator<Document> cursor = rs.iterator();

		while (cursor.hasNext()) {

			Document doc = cursor.next();

			if (doc == null) {
				doc = new Document();
			}

			String singer = doc.getString("singer");
			int singerCnt = doc.getInteger("singerCnt", 0);

			log.info("singer : " + singer);
			log.info("singerCnt : " + singerCnt);

			Map<String, Object> rMap = new LinkedHashMap<String, Object>();

			rMap.put("singer", singer);
			rMap.put("singerCnt", singerCnt);

			rList.add(rMap);

			rMap = null;
			doc = null;
		}

		cursor = null;
		rs = null;
		col = null;
		pipeline = null;

		log.info(this.getClass().getName() + ".getSingerSongCnt End!");

		return rList;
	}

}
