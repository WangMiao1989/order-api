package com.wm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.wm.mapper")
public class Application {
	public static void main(String[] args) {
		 SpringApplication.run(Application.class, args);
//		 String[] writerFormatNames = ImageIO.getWriterFormatNames();
//	     System.out.println("Supported formats for writing: " + Arrays.toString(writerFormatNames));
	}
	
//	@PostConstruct
//    public void initImageIO() {
//        // 扫描并注册所有可用的 ImageIO 插件（包括 WebP）
//        ImageIO.scanForPlugins();
//    }
}
