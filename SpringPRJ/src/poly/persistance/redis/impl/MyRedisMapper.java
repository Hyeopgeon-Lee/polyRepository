package poly.persistance.redis.impl;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import poly.dto.MyJsonDTO;
import poly.persistance.redis.IMyRedisMapper;
import poly.util.CmmUtil;

@Component("MyRedisMapper")
public class MyRedisMapper implements IMyRedisMapper {

	@Autowired
	public RedisTemplate<String, Object> redisDB;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void doSaveData() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".getCacheData service start!");

		String redisKey = "Test01";
		String saveData = "난 저장되는 데이터이다.";

		/*
		 * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
		 */
		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
		redisDB.setValueSerializer(new StringRedisSerializer()); // String 타입

		/*
		 * 2 데이터가 존재하면 바로 반환
		 */
		if (redisDB.hasKey(redisKey)) {
			String res = (String) redisDB.opsForValue().get(redisKey);

			log.info("res : " + res);

		} else {
			redisDB.opsForValue().set(redisKey, saveData);

			redisDB.expire(redisKey, 2, TimeUnit.DAYS);

			log.info("No Data!!");

		}

	}

	/**
	 * 리스트 형태 데이터 저장하기
	 * 
	 */
	@Override
	public void doSaveDataforList() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".doSaveDataforList start!");

		String redisKey = "Test02-List";

		/*
		 * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
		 */
		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
		redisDB.setValueSerializer(new StringRedisSerializer()); // String 타입

		if (redisDB.hasKey(redisKey)) {

			// Redis에 저장된 데이터 전체 가져오기
			// 데이터 인덱스는 0부터 시작하며, 세번째 인자값은 -1로 설정하면 모두 가져옴
			List<String> pList = (List) redisDB.opsForList().range(redisKey, 0, -1);

			Iterator<String> it = pList.iterator();

			while (it.hasNext()) {
				String data = (String) it.next();

				log.info("data : " + data);

			}

		} else {

			// List를 배열과 같이 여러 개의 데이터가 생성되기 때문에 반복문을 통해 데이터 저장
			for (int i = 0; i < 10; i++) {

				// 오름차순 저장
//				redisDB.opsForList().rightPush(redisKey, "[" + i + "] 번째 데이터입니다.");

				// 내림차순 저장
				redisDB.opsForList().leftPush(redisKey, "[" + i + "] 번째 데이터입니다.");

			}

			// 저장되는 데이터의 유효기간(TTL)은 5시간으로 정의
			redisDB.expire(redisKey, 5, TimeUnit.HOURS);

			log.info("Save Data!!");

		}
	}

	/**
	 * DTO를 활용한 JSON 데이터 저장
	 */
	@Override
	public void doSaveDataforListJSON() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".doSaveDataforList start!");

		String redisKey = "Test02-List-JSON";

		/*
		 * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
		 */
		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입

		// DTO를 JSON 구조로 변경
		redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MyJsonDTO.class));

		MyJsonDTO pDTO = null;

		if (redisDB.hasKey(redisKey)) {

			// Redis에 저장된 데이터 전체 가져오기
			// 데이터 인덱스는 0부터 시작하며, 세번째 인자값은 -1로 설정하면 모두 가져옴
			List<MyJsonDTO> pList = (List) redisDB.opsForList().range(redisKey, 0, -1);

			Iterator<MyJsonDTO> it = pList.iterator();

			while (it.hasNext()) {
				MyJsonDTO rDTO = (MyJsonDTO) it.next();

				if (rDTO == null) {
					rDTO = new MyJsonDTO();

				}

				log.info("name : " + CmmUtil.nvl(rDTO.getName()));
				log.info("email : " + CmmUtil.nvl(rDTO.getEmail()));
				log.info("addr : " + CmmUtil.nvl(rDTO.getAddr()));

			}

		} else {

			pDTO = new MyJsonDTO();

			pDTO.setName("이협건");
			pDTO.setEmail("hglee67@kopo.ac.kr");
			pDTO.setAddr("서울시 강서구");

			redisDB.opsForList().rightPush(redisKey, pDTO);

			pDTO = null;

			pDTO = new MyJsonDTO();

			pDTO.setName("홍길동");
			pDTO.setEmail("dkhong@kopo.ac.kr");
			pDTO.setAddr("서울시 양천구");

			redisDB.opsForList().rightPush(redisKey, pDTO);

			pDTO = null;

			// 저장되는 데이터의 유효기간(TTL)은 100분으로 정의
			redisDB.expire(redisKey, 100, TimeUnit.MINUTES);

			log.info("Save Data!!");

		}

	}

	/**
	 * HashTable 저장
	 */
	@Override
	public void doSaveDataforHashTable() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".doSaveDataforHashTable start!");

		String redisKey = "Test03-HashTable";

		redisDB.setKeySerializer(new StringRedisSerializer());
		redisDB.setValueSerializer(new StringRedisSerializer());

		if (redisDB.hasKey(redisKey)) {

			String name = CmmUtil.nvl((String) redisDB.opsForHash().get(redisKey, "name"));
			String email = CmmUtil.nvl((String) redisDB.opsForHash().get(redisKey, "email"));
			String addr = CmmUtil.nvl((String) redisDB.opsForHash().get(redisKey, "addr"));

			log.info("name : " + name);
			log.info("email : " + email);
			log.info("addr : " + addr);

		} else {

			redisDB.opsForHash().put(redisKey, "name", "이협건");
			redisDB.opsForHash().put(redisKey, "email", "hglee67@kopo.ac.kr");
			redisDB.opsForHash().put(redisKey, "addr", "서울시 강서구");

			// 저장되는 데이터의 유효기간(TTL)은 100분으로 정의
			redisDB.expire(redisKey, 100, TimeUnit.MINUTES);

			log.info("Save Data!!");

		}

	}

	@Override
	public void doSaveDataforSet() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".doSaveDataforSet start!");

		String redisKey = "Test03-Set";

		/*
		 * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
		 */
		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
		redisDB.setValueSerializer(new StringRedisSerializer());

		if (redisDB.hasKey(redisKey)) {

			Set rSet = (Set) redisDB.opsForSet().members(redisKey);

			Iterator<String> it = rSet.iterator();

			while (it.hasNext()) {
				String value = CmmUtil.nvl((String) it.next());

				log.info("value" + value);

			}

		} else {

			redisDB.opsForSet().add(redisKey, "1번째 저장합니다.");
			redisDB.opsForSet().add(redisKey, "2번째 저장합니다.");
			redisDB.opsForSet().add(redisKey, "3번째 저장합니다.");
			redisDB.opsForSet().add(redisKey, "4번째 저장합니다.");

			redisDB.expire(redisKey, 100, TimeUnit.MINUTES);

			log.info("Save Data!!");

		}
	}

	@Override
	public void doSaveDataforZSet() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".doSaveDataforSet start!");

		String redisKey = "Test04-Zset";

		/*
		 * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
		 */
		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
		redisDB.setValueSerializer(new StringRedisSerializer());

		if (redisDB.hasKey(redisKey)) {

			// 저장된 전체 레코드 수
			long cnt = redisDB.opsForZSet().size(redisKey);

			Set rSet = (Set) redisDB.opsForZSet().range(redisKey, 0, cnt);

			if (rSet == null) {
				rSet = new LinkedHashSet<String>();
			}

			Iterator<String> it = rSet.iterator();

			while (it.hasNext()) {
				String value = CmmUtil.nvl((String) it.next());

				log.info("value" + value);

			}

		} else {

			// 3번째 파라미터는 저장 순서 가중치이며, 작을수록 앞에 저장됨
			redisDB.opsForZSet().add(redisKey, "1번째 저장합니다.", 1);
			redisDB.opsForZSet().add(redisKey, "2번째 저장합니다.", 2);
			redisDB.opsForZSet().add(redisKey, "3번째 저장합니다.", 3);
			redisDB.opsForZSet().add(redisKey, "4번째 저장하지만, 2번째 위치로 가고 싶다.", 1.1);

			redisDB.expire(redisKey, 100, TimeUnit.MINUTES);

			log.info("Save Data!!");

		}
	}

}
