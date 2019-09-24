package com.zed.springbatchdemo.job2.processor;

import com.zed.springbatchdemo.job2.model.LogData;
import org.springframework.batch.item.ItemProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zeluo
 * @date 2019/9/24 10:00
 */
public class Job2Processor implements ItemProcessor<String, LogData> {
    private static final String requestId = "\\w*-\\w*-\\w*-\\w*-\\w*";
    private static final String requestArg = "arg\\[[0-9]*\\]:\\s.*";
    private static final String responseArg = "response params: \\{.*\\}";//response params 后面是json对象格式

    private static Pattern pattern_requestId;
    private static Pattern pattern_responseArg;
    private static Pattern pattern_requestArg;
    static {
        pattern_requestId = Pattern.compile(requestId);
        pattern_responseArg = Pattern.compile(responseArg);
        pattern_requestArg = Pattern.compile(requestArg);
    }

    @Override
    public LogData process(String str) throws Exception {
        LogData logData = new LogData();
        Matcher matcher_requestId = pattern_requestId.matcher(str);
        Matcher matcher_responseArg = pattern_responseArg.matcher(str);
        Matcher matcher_requestArg = pattern_requestArg.matcher(str);

        if (matcher_requestId.find())
            logData.setRequestId(matcher_requestId.group());
        if (matcher_requestArg.find()) {
            String target_str_request = matcher_requestArg.group();
            int end = target_str_request.indexOf("access");
            String substring = target_str_request.substring(0, end);
            logData.setRequestParams(substring);
        }
        if (matcher_responseArg.find())
            logData.setResponseParams(matcher_responseArg.group());

        return logData;
    }
}
