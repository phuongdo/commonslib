package commons.dns;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DnsHelper {

    public void updateDns( Set<String> dnsSet ) throws Exception{

        String fileHosts = "/etc/hosts";
        if (SystemUtils.IS_OS_WINDOWS) {
            fileHosts = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        } else if (SystemUtils.IS_OS_MAC) {
            // none
        } else if (SystemUtils.IS_OS_LINUX) {

        } else {

        }

        File file = new File(fileHosts);
        List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());
        for (String dns : dnsSet) {
            if (!lines.contains(dns)) {
                FileUtils.writeStringToFile(file, dns + "" + System.getProperty("line.separator"), Charset.defaultCharset(), true);
            }
        }
    }
}
