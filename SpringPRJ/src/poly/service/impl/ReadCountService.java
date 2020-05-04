package poly.service.impl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import poly.dto.TestDTO;
import poly.service.IReadCountService;

@Service("ReadCountService")
public class ReadCountService implements IReadCountService {

	//하둡 마스터 노드 정보
	final private String hdfsIP = "118.219.232.183";
	final private String hdfsPort = "9100";
	
	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MongoTemplate mongodb;

	private String getReadCountForHDFS() throws Exception {
		
		try {

			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", "hdfs://"+ hdfsIP +":"+ hdfsPort);
			FileSystem hdfs = FileSystem.get(conf);

			System.out.println(hdfs.getHomeDirectory());
			System.out.println(hdfs.getWorkingDirectory());
			
			
			Path path = new Path("/user/root/2020/01/29/AN01_20200129");
			//Path localPath = new Path("C:\\Users\\hglee\\Downloads\\Test_Data.csv");
			
			System.out.println(hdfs.exists(path));
			if (hdfs.exists(path)) {
				//hdfs.delete(path, true);
				System.out.println("$$$$$$$$$$$$$$$$$$$!@#$@#$!@$#!@$@#$");
				
			}
			
			// 파일 업로드 끝!
			//hdfs.copyFromLocalFile(localPath, path);
			
			System.out.println("Local File Upload Finished!!");
			
			hdfs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return "";
				
	}
	
	/**
	 * PostConstruct은 처음 한번만 실행되도록 하는 어노테이션
	 * 
	 * 데이터 사전 변수 생성을 위한 함수 최초 스프링 호출이 발생할때 함수가 실행 실행 이후 더이상 실행은 하지 않는다.
	 * 
	 * NLP_DIC 변수에 값을 저장함
	 */
	@Override
	public int Hdfs2MongoForReadCount() throws Exception {

		log.info(this.getClass().getName() + ".Hdfs2MongoForReadCount start!");

		getReadCountForHDFS();
		
		TestDTO pDTO = new TestDTO();
		
		pDTO.setSeq1("1");
		pDTO.setSeq2("2");
		pDTO.setSeq3("3");
		pDTO.setSeq4("4");
		pDTO.setSeq5("5");
		pDTO.setSeq6("6");
		pDTO.setSeq7("7");
		pDTO.setSeq8("8");
		
		mongodb.insert(pDTO, "Test01");
		
		log.info(this.getClass().getName() + ".Hdfs2MongoForReadCount End!");
		return 0;
	}

}
