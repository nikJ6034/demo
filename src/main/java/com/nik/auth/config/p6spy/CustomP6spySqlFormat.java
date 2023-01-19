package com.nik.auth.config.p6spy;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

import org.hibernate.engine.jdbc.internal.FormatStyle;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class CustomP6spySqlFormat implements MessageFormattingStrategy {

    private final List<String> DENIED_FILTER = Arrays.asList("Test1", this.getClass().getSimpleName());
    private final String ALLOW_FILTER = "com.nick";

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared,
            String sql, String url) {
        sql = formatSql(category, sql);
        if(sql!= null && sql.trim().isEmpty()){
            return "";
        }else{
            //sql = sql + createStack(connectionId, elapsed);
        }
        return now + "|" + elapsed + "ms|" + category + "|connection " + connectionId + "|" + sql;
    }

    private String formatSql(String category,String sql) {
        if(sql ==null || sql.trim().equals("")) return sql;

        // Only format Statement, distinguish DDL And DML
        if (Category.STATEMENT.getName().equals(category)) {
            String tmpsql = sql.trim().toLowerCase(Locale.ROOT);
            if(tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
                sql = FormatStyle.DDL.getFormatter().format(sql);
            }else {
                sql = FormatStyle.BASIC.getFormatter().format(sql);
            }
        }

        return sql;
    }

    // stack 콘솔 표기
    private String createStack(int connectionId, long elapsed) {
        Stack<String> callStack = new Stack<>();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTrace) {
            String trace = stackTraceElement.toString();

            // trace 항목을 보고 내게 맞는 것만 필터
            if(trace.startsWith(ALLOW_FILTER) && !filterDenied(trace)) {
                callStack.push(trace);
            }
        }

        StringBuilder sb = new StringBuilder();
        int order = 1;
        while (callStack.size() != 0) {
            sb.append("\n\t\t" + (order++) + "." + callStack.pop());
        }

        return new StringBuffer().append("\n\n\tConnection ID:").append(connectionId)
                .append(" | Excution Time:").append(elapsed).append(" ms\n")
                .append("\n\tExcution Time:").append(elapsed).append(" ms\n")
                .append("\n\tCall Stack :").append(sb).append("\n")
                .append("\n--------------------------------------")
                .toString();
    }

    private boolean filterDenied(String trace){
        for(String denied : DENIED_FILTER){
            boolean startsWith = trace.startsWith(denied);
            if(startsWith){
                return false;
            }
        }

        return true;
    }
    
}
