package gyqx.spdherp.spdOutPtl.service.impl;

import com.google.common.io.CharStreams;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import gyqx.spdherp.spdOutPtl.vo.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cjzyw on 2018/7/13.
 */
public class GroovyService {
        public static final Logger logger = LoggerFactory.getLogger(GroovyService.class);

        private static GroovyClassLoader loader;

        private static final ConcurrentHashMap<String,GroovyObject> groovyObjects = new ConcurrentHashMap<>();

        static {
            PathMatchingResourcePatternResolver resolver = new  PathMatchingResourcePatternResolver();
            loader = new GroovyClassLoader(GroovyService.class.getClassLoader());
            try {
                //适配读取classpath下面的*.groovy文件列表
                Resource[] resources = resolver.getResources("classpath:groovyptl/*.groovy");
                if(resources==null || resources.length==0){
                    logger.warn("未获取到groovy文件");
                }else{
                    for (Resource resource: resources) {
                        try ( InputStream inputStream = GroovyService.class.getClassLoader()
                                .getResourceAsStream("groovyptl/" + resource.getFilename())){
                            logger.info("加载获取JDE货位信息groovy脚本:{}", resource.getFilename());
                            String text = CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
                            Class groovyClass = loader.parseClass(text,resource.getFilename());
                            GroovyObject scriptInstance = (GroovyObject)groovyClass.newInstance();
                            groovyObjects.put(resource.getFilename().split("\\.")[0],scriptInstance);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("groovy 引擎初始化失败！！！！！！！！",e);
            }
        }

    /**
     *
     * @param scriptFileName
     * @param scriptFunctionName 文件名不需要带.groovy后缀
     * @param objects
     * @return
     * @throws Exception
     */
        public MessageResponse execute(String scriptFileName, String scriptFunctionName, Object... objects) throws Exception {
            //scriptFileName:文件名,scriptFunctionName:文件的方法,objects:参数
            MessageResponse response = (MessageResponse)groovyObjects.get(scriptFileName).invokeMethod(scriptFunctionName, objects);
            return response;
        }
    }
