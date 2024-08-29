
package icu.hilin.dns;

import org.xbill.DNS.Record;
import org.xbill.DNS.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // 创建一个DNS报文解析器
        Resolver resolver = new SimpleResolver();

        Message query = Message.newQuery(Record.newRecord(Name.fromString("hilin.icu."), Type.A, DClass.IN));
        Message response = resolver.send(query);

        // 解析响应中的资源记录
        Record[] answers = response.getSectionArray(Section.ANSWER);
        for (Record answer : answers) {
            if (answer.getType() == Type.A) {
                ARecord aRecord = (ARecord) answer;
                System.out.println("IP Address: " + aRecord.getAddress().getHostAddress());
            }
        }
    }


}
